package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    private final FacilityScoreRepository repo;
    private final PropertyRepository propertyRepo;

    public FacilityScoreController(FacilityScoreRepository repo, PropertyRepository propertyRepo) {
        this.repo = repo;
        this.propertyRepo = propertyRepo;
    }

    @PostMapping("/{propertyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public FacilityScore create(@PathVariable Long propertyId,
                                @RequestBody FacilityScore s) {
        Property p = propertyRepo.findById(propertyId).orElseThrow();
        if (repo.findByProperty(p).isPresent()) throw new IllegalArgumentException();
        s.setProperty(p);
        return repo.save(s);
    }

    @GetMapping("/{propertyId}")
    public FacilityScore get(@PathVariable Long propertyId) {
        Property p = propertyRepo.findById(propertyId).orElseThrow();
        return repo.findByProperty(p).orElseThrow();
    }
}
