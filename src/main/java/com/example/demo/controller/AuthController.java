package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwt;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository userRepository,
                          AuthenticationManager authManager,
                          JwtTokenProvider jwt,
                          PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authManager = authManager;
        this.jwt = jwt;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> register(@RequestBody RegisterRequest r) {
        User u = new User();
        u.setName(r.getName());
        u.setEmail(r.getEmail());
        u.setRole(r.getRole());
        u.setPassword(encoder.encode(r.getPassword()));
        userRepository.save(u);
        String token = jwt.generateToken(
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(r.getEmail(), r.getPassword())), u);
        return Map.of("token", token);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest r) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(r.getEmail(), r.getPassword()));
        User u = userRepository.findByEmail(r.getEmail()).orElseThrow();
        return Map.of("token", jwt.generateToken(null, u));
    }
}
