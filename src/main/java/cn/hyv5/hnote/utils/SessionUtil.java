package cn.hyv5.hnote.utils;

import java.util.Optional;

import cn.hyv5.hnote.entity.bo.login.LoginClient;
import cn.hyv5.hnote.entity.bo.login.LoginInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONUtil;
import cn.hyv5.hnote.entity.bo.login.TinyUser;
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
    @Value("${app.cache.user_cache_key}")
    private String userCacheKey;

    @Resource
    private RedisTemplate<String, LoginClient> redisTemplate;


    public Optional<String> getAuthorization(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }else {
            return Optional.empty();
        }
    }
    public void setLogin(User user, LoginClient client) {
        //var tinyUser = new TinyUser(user);
        var sessionSet = redisTemplate.boundSetOps(sessionPrefix + user.getId());
        sessionSet.add(client);
        var userCacheHash = redisTemplate.boundHashOps(userCacheKey);
        userCacheHash.put(user.getId(), user);
    }

    public Optional<User> getUser(){
        var loginInfo = getLoginInfo();
        var userCacheHash = redisTemplate.boundHashOps(userCacheKey);
        return loginInfo
                .map(info->info.getUser())
                .map(TinyUser::getId)
                .map(id->(User)userCacheHash.get(id));

    }

    public Optional<LoginInfo> getLoginInfo(){
        //获取Token
        var request = SpringUtils.getRequest();
        var authorization = getAuthorization(request);
        if(authorization.isEmpty()){
            return Optional.empty();
        }
        //验证，解密
        var aes = new SymmetricCrypto(SymmetricAlgorithm.AES, tokenSecret.getBytes());
        var json = "";
        try{
            json = aes.decryptStr(authorization.get());
        }catch(Exception e) {
            return Optional.empty();
        }
        //获取Token对应的User
        var user = JSONUtil.toBean(json, TinyUser.class);

        var sessionSet = redisTemplate.boundSetOps(sessionPrefix + user.getId());

        var size = sessionSet.size();

        if(size == null || size == 0) {
            return Optional.empty();
        }

        var loginSessionValue = new LoginInfo(user, sessionSet.members());

    return Optional.ofNullable(loginSessionValue);
    }
}
