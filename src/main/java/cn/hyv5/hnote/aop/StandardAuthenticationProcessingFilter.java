package cn.hyv5.hnote.aop;

import cn.hutool.core.util.EnumUtil;
import cn.hyv5.hnote.entity.bo.login.LoginClient;
import cn.hyv5.hnote.entity.dto.LoginRequest;
import cn.hyv5.hnote.enums.UserPrincipalType;
import cn.hyv5.hnote.exception.LoginException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.Optional;

public class StandardAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    public static final String USERNAME_FIELD = "account";
    public static final String PASSWORD_FIELD = "password";
    public static final String PRINCIPAL_TYPE_FIELD = "type";
    public StandardAuthenticationProcessingFilter() {
        super("/login/standard");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(!request.getMethod().equals("POST")) {
            throw LoginException.LOGIN_METHOD_ERR;
        }
        var account = Optional.ofNullable(request.getParameter(USERNAME_FIELD)).orElse("").trim();
        var password = Optional.ofNullable(request.getParameter(PASSWORD_FIELD)).orElse("").trim();
        var type = Optional.ofNullable(request.getParameter(PRINCIPAL_TYPE_FIELD)).orElse("").trim();
        UserPrincipalType principalType = null;
        try{
            principalType = EnumUtil.getBy(UserPrincipalType::getValue, type);
        }catch (IllegalArgumentException e) {
            throw new RuntimeException("not support login type");
        }
        if (principalType == null) {
            throw new RuntimeException("not support login type");
        }
        var loginRequest = new LoginRequest(account, principalType, password, new LoginClient());
        loginRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return getAuthenticationManager().authenticate(loginRequest);
    }

}
