package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RatingService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final PropertyRepository propRepo;
    private final FacilityScoreRepository scoreRepo;
    private final RatingService service;

    public RatingController(PropertyRepository p, FacilityScoreRepository s, RatingService r) {
        this.propRepo = p;
        this.scoreRepo = s;
        this.service = r;
    }

    @PostMapping("/generate/{id}")
    public ResponseEntity<?> generate(@PathVariable Long id) {
        Property p = propRepo.findById(id).orElseThrow();
        FacilityScore s = scoreRepo.findByProperty(p).orElseThrow();
        return ResponseEntity.status(201).body(service.generate(p, s));
    }

    @GetMapping("/property/{id}")
    public RatingResult get(@PathVariable Long id) {
        return propRepo.findById(id).orElseThrow().getRatingResult();
    }
}
