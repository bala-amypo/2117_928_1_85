package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

private final String SECRET_KEY = "MY_SECRET_KEY_123";

public String generateToken(String username) {

return Jwts.builder()
.setSubject(username)
.setIssuedAt(new Date())
.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
.compact();
}

public String extractUsername(String token) {
return extractClaims(token).getSubject();
}

public boolean validateToken(String token) {
return extractClaims(token)
.getExpiration()
.after(new Date());
}

private Claims extractClaims(String token) {
return Jwts.parser()
.setSigningKey(SECRET_KEY)
.parseClaimsJws(token)
.getBody();
}
}