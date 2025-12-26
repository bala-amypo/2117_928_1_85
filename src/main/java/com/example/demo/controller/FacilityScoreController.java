package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class FacilityScoreController {

    private final FacilityScoreService facilityScoreService;

    public FacilityScoreController(FacilityScoreService facilityScoreService) {
        this.facilityScoreService = facilityScoreService;
    }

    @PostMapping("/{propertyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public FacilityScore create(@PathVariable Long propertyId,
                                @RequestBody FacilityScore score) {
        return facilityScoreService.createScore(propertyId, score);
    }

    @GetMapping("/{propertyId}")
    public FacilityScore get(@PathVariable Long propertyId) {
        return facilityScoreService.getScore(propertyId);
    }
}
