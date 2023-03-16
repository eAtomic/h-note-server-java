package cn.hyv5.hnote.aop;

import java.io.IOException;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.hutool.core.util.StrUtil;
import cn.hyv5.hnote.utils.SpringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 获取AuthorizationToken
            String authorization = getAuthorization(request);
            // 从缓存中获取用户信息
            //UserDetails userDetails = (UserDetails)redisTemplate.opsForValue().get(authorization);
            var tokenHash = redisTemplate.boundHashOps("login_session");
            UserDetails userDetails = (UserDetails)tokenHash.get(authorization);
            Assert.notNull(userDetails,"登录已过期请重新登录");

            // 构建AuthenticationToken
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 把AuthenticationToken放到当前线程,表示认证完成
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (SecurityException e) {
            //ResponseUtil.renderJson(response, e);
            SpringUtils.renderResult(e.getMessage());
        }
    }
    
}
