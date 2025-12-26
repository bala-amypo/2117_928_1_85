package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;
    private final JwtTokenProvider jwt;

    public AuthController(UserRepository repo, PasswordEncoder encoder, AuthenticationManager manager, JwtTokenProvider jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.manager = manager;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest r) {
        User u = new User();
        u.setName(r.getName());
        u.setEmail(r.getEmail());
        u.setRole(r.getRole());
        u.setPassword(encoder.encode(r.getPassword()));
        repo.save(u);
        Authentication a = manager.authenticate(new UsernamePasswordAuthenticationToken(r.getEmail(), r.getPassword()));
        return ResponseEntity.status(201).body(Map.of("token", jwt.generateToken(a, u)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest r) {
        Authentication a = manager.authenticate(new UsernamePasswordAuthenticationToken(r.getEmail(), r.getPassword()));
        User u = repo.findByEmail(r.getEmail()).orElseThrow();
        return ResponseEntity.ok(Map.of("token", jwt.generateToken(a, u)));
    }
}
