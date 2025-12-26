package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    private final FacilityScoreRepository repo;
    private final PropertyRepository propRepo;

    public FacilityScoreController(FacilityScoreRepository r, PropertyRepository p) {
        this.repo = r;
        this.propRepo = p;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> add(@PathVariable Long id, @RequestBody FacilityScore s) {
        Property p = propRepo.findById(id).orElseThrow();
        if (repo.findByProperty(p).isPresent()) return ResponseEntity.badRequest().build();
        s.setProperty(p);
        return ResponseEntity.status(201).body(repo.save(s));
    }

    @GetMapping("/{id}")
    public FacilityScore get(@PathVariable Long id) {
        return repo.findByProperty(propRepo.findById(id).orElseThrow()).orElseThrow();
    }
}
