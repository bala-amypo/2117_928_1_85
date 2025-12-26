package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class JwtTokenProvider {

    private final String secret = "secret";

    public String generateToken(Authentication auth, User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        return ((Number) Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().get("id")).longValue();
    }
}
