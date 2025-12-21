package com.example.demo.service.impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
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
                .findFirst()
                .orElseThrow(() -> new RuntimeException("FacilityScore not found"));

        double avg = (
                s.getSchoolProximity()
              + s.getHospitalProximity()
              + s.getTransportAccess()
              + s.getSafetyScore()
        ) / 4.0;

        RatingResult r = new RatingResult();
        r.setProperty(propertyRepo.findById(propertyId).orElseThrow());
        r.setFinalRating(avg);

        if (avg >= 8) {
            r.setRatingCategory("EXCELLENT");
        } else if (avg >= 6) {
            r.setRatingCategory("GOOD");
        } else if (avg >= 4) {
            r.setRatingCategory("AVERAGE");
        } else {
            r.setRatingCategory("POOR");
        }

        return ratingRepo.save(r);
    }
}
