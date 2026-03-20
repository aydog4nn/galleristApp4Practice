package com.aydog4nn.jwt;

import com.aydog4nn.exception.BaseException;
import com.aydog4nn.exception.ErrorMessage;
import com.aydog4nn.exception.MessageType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JWTService jwtService;


    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


            String header = request.getHeader("Authorization");
            if (header == null){
                filterChain.doFilter(request,response);
                return;
            }
            String token;
            String username;

            token = header.substring(7);
            try {
                username = jwtService.getUsernameByToken(token);
                if (username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtService.isTokenValid(token)){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());

                        usernamePasswordAuthenticationToken.setDetails(userDetails);
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }

                }
            }
            catch (ExpiredJwtException exception){
                throw new BaseException(new ErrorMessage(exception.getMessage(), MessageType.TOKEN_IS_EXPIRED));
            }
            catch (Exception e){
                throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.GENEREAL_EXCEPTION));
            }
            filterChain.doFilter(request,response);
    }
}
