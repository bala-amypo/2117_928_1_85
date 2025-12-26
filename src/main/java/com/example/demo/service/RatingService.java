package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    
    @Autowired
    private RatingResultRepository ratingResultRepository;
    
    @Autowired
    private FacilityScoreRepository facilityScoreRepository;
    
    public RatingResult generateRating(Property property) {
        Optional<FacilityScore> scoreOpt = facilityScoreRepository.findByProperty(property);
        if (scoreOpt.isEmpty()) {
            throw new RuntimeException("No facility score found for property");
        }
        
        FacilityScore score = scoreOpt.get();
        double avgScore = (score.getSchoolProximity() + score.getHospitalProximity() + 
                          score.getTransportAccess() + score.getSafetyScore()) / 4.0;
        
        String category = determineRatingCategory(avgScore);
        
        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(avgScore);
        result.setRatingCategory(category);
        
        return ratingResultRepository.save(result);
    }
    
    public List<RatingResult> getAllRatings() {
        return ratingResultRepository.findAll();
    }
    
    public Optional<RatingResult> getRatingById(Long id) {
        return ratingResultRepository.findById(id);
    }
    
    public Optional<RatingResult> getRatingByProperty(Property property) {
        return ratingResultRepository.findByProperty(property);
    }
    
    public void deleteRating(Long id) {
        ratingResultRepository.deleteById(id);
    }
    
    private String determineRatingCategory(double avgScore) {
        if (avgScore >= 8) return "EXCELLENT";
        else if (avgScore >= 6) return "GOOD";
        else if (avgScore >= 4) return "AVERAGE";
        else return "POOR";
    }
}