package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final PropertyRepository propertyRepo;
    private final FacilityScoreRepository scoreRepo;
    private final RatingResultRepository resultRepo;

    public RatingController(PropertyRepository propertyRepo,
                            FacilityScoreRepository scoreRepo,
                            RatingResultRepository resultRepo) {
        this.propertyRepo = propertyRepo;
        this.scoreRepo = scoreRepo;
        this.resultRepo = resultRepo;
    }

    @PostMapping("/generate/{propertyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public RatingResult generate(@PathVariable Long propertyId) {
        Property p = propertyRepo.findById(propertyId).orElseThrow();
        FacilityScore s = scoreRepo.findByProperty(p).orElseThrow();
        double avg = (s.getSchoolProximity() + s.getHospitalProximity()
                + s.getTransportAccess() + s.getSafetyScore()) / 4.0;
        RatingResult r = new RatingResult();
        r.setProperty(p);
        r.setFinalRating(avg);
        r.setRatingCategory(avg >= 8 ? "EXCELLENT" : avg >= 6 ? "GOOD" : "POOR");
        return resultRepo.save(r);
    }

    @GetMapping("/property/{propertyId}")
    public RatingResult get(@PathVariable Long propertyId) {
        Property p = propertyRepo.findById(propertyId).orElseThrow();
        return resultRepo.findByProperty(p).orElseThrow();
    }
}
