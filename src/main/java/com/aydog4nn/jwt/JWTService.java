package com.aydog4nn.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTService {

    @Value("${jwt-key}")
    public String key;

    public String generateToken(UserDetails userDetails){
         return Jwts.builder().
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2)).
                signWith(getKey(), SignatureAlgorithm.HS256).
                compact();

    }

    public Claims getClaims(String token){

        return Jwts.parserBuilder().
                setSigningKey(getKey()).
                build().
                parseClaimsJws(token).
                getBody();
    }

    public <T> T exportToken(String token, Function<Claims,T> claimsTFunction){
         Claims claims =  getClaims(token);
         return claimsTFunction.apply(claims);
    }


    public Key getKey(){
        byte[] bytes =  Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(bytes);
    }


    public String getUsernameByToken(String token){
        return exportToken(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token){
        Date expiredDate = exportToken(token,Claims::getExpiration);
        return new Date().before(expiredDate);

    }

}
