package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingResultRepository ratingResultRepository;

    public RatingResult generate(Property property, FacilityScore s) {
        double finalRating = (s.getSchoolProximity() + s.getHospitalProximity() + s.getTransportAccess() + s.getSafetyScore()) / 4.0;

        RatingResult rr = new RatingResult();
        rr.setProperty(property);
        rr.setFinalRating(finalRating);

        if (finalRating >= 8) rr.setRatingCategory("EXCELLENT");
        else if (finalRating >= 6) rr.setRatingCategory("GOOD");
        else if (finalRating >= 4) rr.setRatingCategory("AVERAGE");
        else rr.setRatingCategory("POOR");

        return ratingResultRepository.save(rr);
    }
}
