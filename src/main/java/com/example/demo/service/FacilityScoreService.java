package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacilityScoreService {

    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;

    public FacilityScoreService(FacilityScoreRepository facilityScoreRepository,
                                PropertyRepository propertyRepository) {
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
    }

    @Transactional
    public FacilityScore createScore(Long propertyId, FacilityScore score) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        if (facilityScoreRepository.findByProperty(property).isPresent()) {
            throw new BadRequestException("Facility score already exists");
        }

        score.setProperty(property);
        return facilityScoreRepository.save(score);
    }

    public FacilityScore getScore(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        return facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }
}
