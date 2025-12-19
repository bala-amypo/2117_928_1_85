package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@Tag(name = "Rating Result")
public class RatingResultController {

    private final RatingResultService service;

    public RatingResultController(RatingResultService service) {
        this.service = service;
    }

    @PostMapping("/generate/{propertyId}")
    @Operation(summary = "Generate rating result")
    public RatingResult generate(@PathVariable Long propertyId) {
        return service.generateRating(propertyId);
    }

    @GetMapping("/property/{propertyId}")
    @Operation(summary = "Get rating result by property")
    public RatingResult get(@PathVariable Long propertyId) {
        return service.getRating(propertyId);
    }
}
