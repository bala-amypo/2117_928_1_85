package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RatingResultService;
import org.springframework.stereotype.Service;

@Service
public class RatingResultServiceImpl implements RatingResultService {

    private final FacilityScoreRepository scoreRepo;
    private final PropertyRepository propertyRepo;
    private final RatingResultRepository ratingRepo;

    public RatingResultServiceImpl(FacilityScoreRepository scoreRepo,
                                   PropertyRepository propertyRepo,
                                   RatingResultRepository ratingRepo) {
        this.scoreRepo = scoreRepo;
        this.propertyRepo = propertyRepo;
        this.ratingRepo = ratingRepo;
    }

    @Override
    public RatingResult generate(Long propertyId) {
        FacilityScore s = scoreRepo.findAll().stream()
                .filter(x -> x.getProperty().getId().equals(propertyId))
                .findFirst().orElseThrow();

        double avg = (s.getSchool() + s.getHospital() + s.getTransport()) / 3.0;

        RatingResult r = new RatingResult();
        r.setProperty(propertyRepo.findById(propertyId).orElseThrow());
        r.setRating(avg);
        r.setCategory(avg >= 7 ? "GOOD" : avg >= 4 ? "AVERAGE" : "POOR");

        return ratingRepo.save(r);
    }
}
