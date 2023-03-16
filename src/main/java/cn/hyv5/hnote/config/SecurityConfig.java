package cn.hyv5.hnote.config;

import cn.hyv5.hnote.aop.StandardAuthenticationFailureHandler;
import cn.hyv5.hnote.aop.StandardAuthenticationProcessingFilter;

import cn.hyv5.hnote.aop.StandardAuthenticationSuccessHandler;
import cn.hyv5.hnote.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Resource
    private StandardAuthenticationSuccessHandler successHandler;
    @Resource
    private StandardAuthenticationFailureHandler failureHandler;
    private static final String[] AUTH_WHITELIST = {
            "/",
            "/index.html",
            // -- Swagger UI v2
            "/v2/api-docs",
            "v2/api-docs",
            "/swagger-resources",
            "swagger-resources",
            "/swagger-resources/**",
            "swagger-resources/**",
            "/configuration/ui",
            "configuration/ui",
            "/configuration/security",
            "configuration/security",
            "/swagger-ui.html",
            "swagger-ui.html",
            "webjars/**",
            // -- Swagger UI v3
            "/v3/api-docs/**",
            "v3/api-docs/**",
            "/swagger-ui/**",
            "swagger-ui/**",
            // CSA Controllers
            "/csa/api/token",
            // Actuators
            "/actuator/**",
            "/health/**"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        StandardAuthenticationProcessingFilter standardAuthenticationProcessingFilter
    ) throws Exception {
        
        http
                //.addFilterBefore(standardAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/")
                .usernameParameter("account")
                .loginProcessingUrl("/login/standard")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                //.loginProcessingUrl("/login/sso")
                .and()
                .authorizeHttpRequests((auth)-> auth
                            .requestMatchers(AUTH_WHITELIST).permitAll()
                            .anyRequest().authenticated()
                )
                .logout()
                .logoutUrl("/logout")
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .contentSecurityPolicy("frame-ancestors 'self'; default-src 'self' 'unsafe-inline' 'unsafe-eval' *.aliyuncs.com *.baidu.com *.bdimg.com ;object-src 'self'")
                .and()
                .and()
                .csrf(AbstractHttpConfigurer::disable)
                ;

        return http.build();
    }
    @Bean
    public StandardAuthenticationProcessingFilter standardAuthenticationProcessingFilter(StandardAuthenticationSuccessHandler successHandler, StandardAuthenticationFailureHandler failureHandler) throws Exception {
        var filter = new StandardAuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        return filter;
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }
}
