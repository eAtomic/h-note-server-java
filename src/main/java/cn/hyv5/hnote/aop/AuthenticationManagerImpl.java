package cn.hyv5.hnote.aop;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import cn.hyv5.hnote.entity.bo.login.LoginClient;
import cn.hyv5.hnote.entity.po.User;
import cn.hyv5.hnote.service.UserService;
import cn.hyv5.hnote.utils.SessionUtil;
import jakarta.annotation.Resource;

@Component
public class AuthenticationManagerImpl implements AuthenticationManager{

    @Resource
    private SessionUtil sessionUtil;
    @Resource
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof PreAuthenticatedAuthenticationToken preAuth) {
            String username = (String)preAuth.getPrincipal();
            String password = (String)preAuth.getCredentials();
            User user = null;
            try{
                user = (User) userService.loadUserByUsername(username);
            }catch(Exception e){
                throw new RuntimeException("user not exists");
            }
            if(user == null) {
                throw new RuntimeException("user not exists");
            }
            var passwordEncoder = new BCryptPasswordEncoder();
            var match = passwordEncoder.matches(password, user.getPassword());
            if(!match) {
                throw new RuntimeException("password incorrect");
            }
            var client = new LoginClient();
            sessionUtil.setLogin(user, null);
        }

        return authentication;
        //throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

}