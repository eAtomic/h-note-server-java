package cn.hyv5.hnote.config;

import cn.hyv5.hnote.aop.StandardAuthenticationProcessingFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig implements SecurityConfigurer{

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final String[] AUTH_WHITELIST = {
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
                .addFilterBefore(standardAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login/standard")
                .loginProcessingUrl("/login/sso")
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
    public StandardAuthenticationProcessingFilter standardAuthenticationProcessingFilter() throws Exception {
        var filter = new StandardAuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(null);
        filter.setAuthenticationFailureHandler(null);
        return filter;
    }

}
