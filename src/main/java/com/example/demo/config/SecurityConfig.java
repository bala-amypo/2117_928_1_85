package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

private final JwtAuthenticationFilter jwtFilter;

public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
this.jwtFilter = jwtFilter;
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

http.csrf(csrf -> csrf.disable())
.authorizeHttpRequests(auth -> auth
.requestMatchers(
"/auth/**",
"/swagger-ui/**",
"/v3/api-docs/**",
"/hello-servlet"
).permitAll()
.anyRequest().authenticated()
)
.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

return http.build();
}

@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

@Bean
public AuthenticationManager authenticationManager(
AuthenticationConfiguration config) throws Exception {
return config.getAuthenticationManager();
}
}



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



package com.example.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

private final JwtUtil jwtUtil;

public JwtAuthenticationFilter(JwtUtil jwtUtil) {
this.jwtUtil = jwtUtil;
}

@Override
protected void doFilterInternal(HttpServletRequest request,
HttpServletResponse response,
FilterChain filterChain)
throws ServletException, IOException {

String authHeader = request.getHeader("Authorization");

if (authHeader != null && authHeader.startsWith("Bearer ")) {

String token = authHeader.substring(7);
String username = jwtUtil.extractUsername(token);

if (username != null && jwtUtil.validateToken(token)) {

UsernamePasswordAuthenticationToken authentication =
new UsernamePasswordAuthenticationToken(
username,
null,
Collections.emptyList()
);

authentication.setDetails(
new WebAuthenticationDetailsSource().buildDetails(request)
);

SecurityContextHolder.getContext().setAuthentication(authentication);
}
}

filterChain.doFilter(request, response);
}
}