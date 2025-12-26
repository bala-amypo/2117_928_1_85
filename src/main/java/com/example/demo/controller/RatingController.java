package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @Autowired
    private FacilityScoreRepository facilityScoreRepository;
    
    @Autowired
    private RatingResultRepository ratingResultRepository;
    
    @PostMapping("/generate/{propertyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> generateRating(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
            .orElse(null);
        
        if (property == null) {
            return ResponseEntity.notFound().build();
        }
        
        FacilityScore score = facilityScoreRepository.findByProperty(property)
            .orElse(null);
        
        if (score == null) {
            return ResponseEntity.badRequest().body("No facility score found for this property");
        }
        
        // Calculate rating based on facility scores
        double avgScore = (score.getSchoolProximity() + score.getHospitalProximity() + 
                          score.getTransportAccess() + score.getSafetyScore()) / 4.0;
        
        String category;
        if (avgScore >= 8) category = "EXCELLENT";
        else if (avgScore >= 6) category = "GOOD";
        else if (avgScore >= 4) category = "AVERAGE";
        else category = "POOR";
        
        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(avgScore);
        result.setRatingCategory(category);
        
        RatingResult saved = ratingResultRepository.save(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<RatingResult> getRating(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
            .orElse(null);
        
        if (property == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ratingResultRepository.findByProperty(property)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}