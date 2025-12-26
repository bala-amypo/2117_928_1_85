package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingResultRepository resultRepo;
    private final RatingLogRepository logRepo;

    public RatingService(RatingResultRepository r, RatingLogRepository l) {
        this.resultRepo = r;
        this.logRepo = l;
    }

    public RatingResult generate(Property p, FacilityScore s) {
        double avg = (s.getSchoolProximity() + s.getHospitalProximity() + s.getTransportAccess() + s.getSafetyScore()) / 4.0;
        RatingResult r = new RatingResult();
        r.setProperty(p);
        r.setFinalRating(avg);
        r.setRatingCategory(avg >= 8 ? "EXCELLENT" : avg >= 6 ? "GOOD" : avg >= 4 ? "AVERAGE" : "POOR");
        resultRepo.save(r);
        RatingLog log = new RatingLog();
        log.setProperty(p);
        log.setMessage("Rating generated");
        logRepo.save(log);
        return r;
    }
}
