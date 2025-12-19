package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/properties")
@Tag(name = "Property")
public class PropertyController {

    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Add property")
    public Property addProperty(@RequestBody Property property) {
        return service.addProperty(property);
    }

    @GetMapping
    @Operation(summary = "List all properties")
    public List<Property> getAll() {
        return service.getAllProperties();
    }
}
