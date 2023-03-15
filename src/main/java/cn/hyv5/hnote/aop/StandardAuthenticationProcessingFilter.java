package cn.hyv5.hnote.aop;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.Optional;

public class StandardAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    public static final String USERNAME_FIELD = "account";
    public static final String PASSWORD_FIELD = "password";
    public StandardAuthenticationProcessingFilter() {
        super("/login/standard");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported:" + request.getMethod());
        }
        var username = Optional.ofNullable(request.getParameter(USERNAME_FIELD)).orElse("").trim();
        var password = Optional.ofNullable(request.getParameter(PASSWORD_FIELD)).orElse("").trim();

        var authRequest = new UsernamePasswordAuthenticationToken(username, password);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return getAuthenticationManager().authenticate(authRequest);
    }

}
