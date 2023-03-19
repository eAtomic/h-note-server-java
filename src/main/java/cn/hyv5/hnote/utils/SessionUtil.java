package cn.hyv5.hnote.utils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cn.hyv5.hnote.entity.bo.login.LoginClient;
import cn.hyv5.hnote.entity.bo.login.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONUtil;
import cn.hyv5.hnote.entity.bo.login.TinyUser;
import cn.hyv5.hnote.entity.po.User;
import cn.hyv5.hnote.service.UserService;
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
    @Value("${app.cache.user_cache_prefix}")
    private String userCachePrefix;
    @Resource
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public static Optional<String> getAuthorization(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }else {
            return Optional.empty();
        }
    }
    public void setLogin(User user, LoginClient client) {
//        var sessionSet = redisTemplate.boundSetOps(sessionPrefix + user.getId());
//        sessionSet.add(client);

        var session = redisTemplate.boundValueOps(sessionPrefix.concat(user.getId()).concat("|").concat(client.getToken().substring(7)));
        session.set(client,20, TimeUnit.MINUTES);
//        var userCacheHash = redisTemplate.boundHashOps(userCacheKey);
//        userCacheHash.put(user.getId(), user);
        var userCache = redisTemplate.boundValueOps(userCachePrefix.concat(user.getId()));
        userCache.set(user, 60, TimeUnit.MINUTES);
    }

    public String makeToken(User user){
        var tinyUser = new TinyUser(user);
        var aes = new SymmetricCrypto(SymmetricAlgorithm.AES, tokenSecret.getBytes());
        var json = JSONUtil.toJsonStr(tinyUser);
        return "Bearer "+aes.encryptBase64(json);
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
        var tinyUser = JSONUtil.toBean(json, TinyUser.class);

//        var sessionSet = redisTemplate.boundSetOps(sessionPrefix + tinyUser.getId());
        var session = redisTemplate.boundValueOps(sessionPrefix.concat(tinyUser.getId()).concat("|").concat(authorization.get()));

        if(session == null || !session.persist()) {
            return Optional.empty();
        }
        var userCache = redisTemplate.boundValueOps(userCachePrefix.concat(tinyUser.getId()));
        var user = (User)userCache.get();
        if(user == null) {
            user = userService.getById(tinyUser.getId());
            userCache.set(user);
        }
        userCache.expire(20, TimeUnit.MINUTES);

        var clients = redisTemplate.keys(sessionPrefix.concat(tinyUser.getId()).concat("|*")).stream().map(key->(LoginClient)redisTemplate.opsForValue().get(key)).collect(Collectors.toSet());

        var loginSessionValue = new LoginInfo(user, clients);

    return Optional.ofNullable(loginSessionValue);
    }
}
