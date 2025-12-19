package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import com.example.demo.service.RatingResultService;
import org.springframework.stereotype.Service;

@Service
public class RatingResultServiceImpl implements RatingResultService {

    private final FacilityScoreRepository scoreRepository;
    private final RatingResultRepository ratingRepository;
    private final PropertyRepository propertyRepository;

    public RatingResultServiceImpl(FacilityScoreRepository scoreRepository,
                                   RatingResultRepository ratingRepository,
                                   PropertyRepository propertyRepository) {
        this.scoreRepository = scoreRepository;
        this.ratingRepository = ratingRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {
        FacilityScore s = scoreRepository.findAll().stream()
                .filter(x -> x.getProperty().getId().equals(propertyId))
                .findFirst().orElseThrow();

        double avg = (s.getSchoolProximity()
                + s.getHospitalProximity()
                + s.getTransportAccess()
                + s.getSafetyScore()) / 4.0;

        String category =
                avg >= 8 ? "EXCELLENT" :
                avg >= 6 ? "GOOD" :
                avg >= 4 ? "AVERAGE" : "POOR";

        RatingResult r = new RatingResult();
        Property p = propertyRepository.findById(propertyId).orElseThrow();
        r.setProperty(p);
        r.setFinalRating(avg);
        r.setRatingCategory(category);
        return ratingRepository.save(r);
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        return ratingRepository.findAll().stream()
                .filter(r -> r.getProperty().getId().equals(propertyId))
                .findFirst().orElseThrow();
    }
}
