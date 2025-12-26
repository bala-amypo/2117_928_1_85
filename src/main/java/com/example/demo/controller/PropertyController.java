package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyService propertyService,
                              PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Property create(@RequestBody @Valid Property property) {
        return propertyService.addProperty(property);
    }

    @GetMapping
    public List<Property> list() {
        return propertyRepository.findAll();
    }
}
