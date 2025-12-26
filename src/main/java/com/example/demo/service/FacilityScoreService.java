package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityScoreService {
    
    @Autowired
    private FacilityScoreRepository facilityScoreRepository;
    
    public FacilityScore createFacilityScore(FacilityScore facilityScore) {
        return facilityScoreRepository.save(facilityScore);
    }
    
    public List<FacilityScore> getAllFacilityScores() {
        return facilityScoreRepository.findAll();
    }
    
    public Optional<FacilityScore> getFacilityScoreById(Long id) {
        return facilityScoreRepository.findById(id);
    }
    
    public Optional<FacilityScore> getFacilityScoreByProperty(Property property) {
        return facilityScoreRepository.findByProperty(property);
    }
    
    public FacilityScore updateFacilityScore(FacilityScore facilityScore) {
        return facilityScoreRepository.save(facilityScore);
    }
    
    public void deleteFacilityScore(Long id) {
        facilityScoreRepository.deleteById(id);
    }
    
    public boolean existsByProperty(Property property) {
        return facilityScoreRepository.existsByProperty(property);
    }
    
    public double calculateAverageScore(FacilityScore score) {
        return (score.getSchoolProximity() + score.getHospitalProximity() + 
                score.getTransportAccess() + score.getSafetyScore()) / 4.0;
    }
}