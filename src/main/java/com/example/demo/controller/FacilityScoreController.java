package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {
    
    @Autowired
    private FacilityScoreRepository facilityScoreRepository;
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @PostMapping("/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createFacilityScore(@PathVariable Long propertyId, 
                                               @Valid @RequestBody FacilityScore score) {
        Property property = propertyRepository.findById(propertyId)
            .orElse(null);
        
        if (property == null) {
            return ResponseEntity.notFound().build();
        }
        
        if (facilityScoreRepository.existsByProperty(property)) {
            return ResponseEntity.badRequest().body("Facility score already exists for this property");
        }
        
        score.setProperty(property);
        FacilityScore saved = facilityScoreRepository.save(score);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping("/{propertyId}")
    public ResponseEntity<FacilityScore> getFacilityScore(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
            .orElse(null);
        
        if (property == null) {
            return ResponseEntity.notFound().build();
        }
        
        return facilityScoreRepository.findByProperty(property)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}