package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
@Tag(name = "Facility Score")
public class FacilityScoreController {

    private final FacilityScoreService service;

    public FacilityScoreController(FacilityScoreService service) {
        this.service = service;
    }

    @PostMapping("/{propertyId}")
    @Operation(summary = "Submit facility score")
    public FacilityScore addScore(@PathVariable Long propertyId,
                                  @RequestBody FacilityScore score) {
        return service.addScore(propertyId, score);
    }

    @GetMapping("/{propertyId}")
    @Operation(summary = "Get facility score by property")
    public FacilityScore getScore(@PathVariable Long propertyId) {
        return service.getScoreByProperty(propertyId);
    }
}
