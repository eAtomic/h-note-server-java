package cn.hyv5.hnote.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONUtil;
import cn.hyv5.hnote.entity.bo.CachedUser;
import cn.hyv5.hnote.entity.po.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 用户处理用户登录状态的类
 */
@Component
public class SessionUtil {
    @Value("${app.session.expire_during}")
    private String expireDuring;
    @Value("${app.token.secret}")
    private String tokenSecret;
    @Value("${app.cache.session_prefix}")
    private String sessionPrefix;
    @Value("${app.cache.user_prefix}")
    private String userPrefix;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public Optional<User> getUser(){
        var request = SpringUtils.getRequest();
        // 获取AuthorizationToken
        String authorization = getAuthorization(request);
        //var loginSet = redisTemplate.boundSetOps()
        // var tokenHash = redisTemplate.boundHashOps(LOGIN_SESSION_KEY);
        // User user = (User)tokenHash.get(authorization);
        // return Optional.ofNullable(user);
    }

    public String getAuthorization(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }else {
            throw new RuntimeException("token invalid");
        }
    }

    public CachedUser getCachedUser(){
        //获取Token
        var request = SpringUtils.getRequest();
        var authorization = getAuthorization(request);
        Assert.notNull(authorization, ()->new RuntimeException("not login"));
        //验证，解密
        var aes = new SymmetricCrypto(SymmetricAlgorithm.AES, tokenSecret.getBytes());
        var json = "";
        try{
            json = aes.decryptStr(authorization);
        }catch(Exception e) {
            throw new RuntimeException("not login");
        }
        //获取Token对应的User
        var user = JSONUtil.toBean(json, CachedUser.class);

        //redisTemplate.boundSetOps(authorization)
    }
}
