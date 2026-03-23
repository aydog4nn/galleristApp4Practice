package com.aydog4nn.config;

import com.aydog4nn.jwt.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String REFRESH_TOKEN = "/refresh-token";

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    // added-refresh-token

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        http.
                csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(request -> request.
                        requestMatchers(AUTHENTICATE,REGISTER,REFRESH_TOKEN).permitAll().
                        anyRequest().
                        authenticated()).
                        exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint)).
                        sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                        authenticationProvider(authenticationProvider).
                        addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }

}
