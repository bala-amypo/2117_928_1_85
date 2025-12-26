package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyRepository repo;

    public PropertyController(PropertyRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Property p) {
        return ResponseEntity.status(201).body(repo.save(p));
    }

    @GetMapping
    public List<Property> list() {
        return repo.findAll();
    }
}
