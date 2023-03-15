package cn.hyv5.hnote.aop;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerImpl implements AuthenticationManager{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication;
        //throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

}