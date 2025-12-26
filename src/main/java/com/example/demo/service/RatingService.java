package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingService {

    private final PropertyRepository propertyRepository;
    private final FacilityScoreRepository facilityScoreRepository;
    private final RatingResultRepository ratingResultRepository;

    public RatingService(PropertyRepository propertyRepository,
                         FacilityScoreRepository facilityScoreRepository,
                         RatingResultRepository ratingResultRepository) {
        this.propertyRepository = propertyRepository;
        this.facilityScoreRepository = facilityScoreRepository;
        this.ratingResultRepository = ratingResultRepository;
    }

    @Transactional
    public RatingResult generateRating(Long propertyId) {

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        FacilityScore fs = facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Facility score missing"));

        double avg = (fs.getSchoolProximity()
                + fs.getHospitalProximity()
                + fs.getTransportAccess()
                + fs.getSafetyScore()) / 4.0;

        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(avg);
        result.setRatingCategory(category(avg));

        return ratingResultRepository.save(result);
    }

    private String category(double rating) {
        if (rating >= 8) return "EXCELLENT";
        if (rating >= 6) return "GOOD";
        if (rating >= 4) return "AVERAGE";
        return "POOR";
    }

    public RatingResult getRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        return ratingResultRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
    }
}
