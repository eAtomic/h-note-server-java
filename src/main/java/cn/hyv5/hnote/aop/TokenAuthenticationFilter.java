package cn.hyv5.hnote.aop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import cn.hyv5.hnote.utils.SessionUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private SessionUtil sessionUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var loginInfo = sessionUtil.getLoginInfo();
        if (loginInfo.isEmpty()){
            filterChain.doFilter(request, response);
            return;
        }
        var tinyUser = loginInfo.get().getUser();
        var authorities = Streams.concat(
                tinyUser.getRoles().stream(),
                tinyUser.getPermissions().stream()
        ).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(tinyUser.getUsername(), null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
    
}
