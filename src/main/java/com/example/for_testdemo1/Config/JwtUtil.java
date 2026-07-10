package com.example.for_testdemo1.Config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }


    public String generateToken(int userId, String account, int role) {
        String token = Jwts.builder()
                .claim("userId", userId)
                .claim("account", account)
                .claim("role", role)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey())
                .compact();
        return token;
    }

    private  Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }

    public void validateToken(String token) {
        getClaims(token);
    }

    public int extractUserId(String token) {
        Claims claims = getClaims(token);
        int userId = claims.get("userId", Integer.class);
        return userId;
    }

    public int extractRole(String token) {
        Claims claims = getClaims(token);
        int role = claims.get("role", Integer.class);
        return role;
    }

}

