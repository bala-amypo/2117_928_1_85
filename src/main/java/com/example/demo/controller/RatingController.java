package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final PropertyRepository propertyRepository;
    private final FacilityScoreRepository facilityScoreRepository;
    private final RatingResultRepository ratingResultRepository;
    private final RatingLogRepository ratingLogRepository;

    public RatingController(PropertyRepository propertyRepository,
                            FacilityScoreRepository facilityScoreRepository,
                            RatingResultRepository ratingResultRepository,
                            RatingLogRepository ratingLogRepository) {
        this.propertyRepository = propertyRepository;
        this.facilityScoreRepository = facilityScoreRepository;
        this.ratingResultRepository = ratingResultRepository;
        this.ratingLogRepository = ratingLogRepository;
    }

    @PostMapping("/generate/{propertyId}")
    public RatingResult generate(@PathVariable Long propertyId) {

        Property property = propertyRepository.findById(propertyId).orElseThrow();

        List<FacilityScore> scores =
                facilityScoreRepository.findByProperty(property);

        double total = 0;
        for (FacilityScore s : scores) {
            total += s.getHospitalProximity()
                   + s.getTransportAccess()
                   + s.getSafetyScore();
        }

        double rating = scores.isEmpty() ? 0 : total / scores.size();

        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setRating(rating);

        ratingResultRepository.save(result);

        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setGeneratedRating(rating);

        ratingLogRepository.save(log);

        return result;
    }

    @GetMapping("/property/{propertyId}")
    public List<RatingResult> getByProperty(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        return ratingResultRepository.findByProperty(property);
    }
}
