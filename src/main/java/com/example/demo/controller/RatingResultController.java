package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingResultService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingResultController {

    private final RatingResultService service;

    public RatingResultController(RatingResultService service) {
        this.service = service;
    }

    @PostMapping("/{propertyId}")
    public RatingResult generate(@PathVariable Long propertyId) {
        return service.generate(propertyId);
    }
}
