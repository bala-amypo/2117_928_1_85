package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyRepository repository;

    public PropertyController(PropertyRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Property create(@RequestBody Property p) {
        if (p.getPrice() <= 0) throw new IllegalArgumentException();
        return repository.save(p);
    }

    @GetMapping
    public List<Property> list() {
        return repository.findAll();
    }
}
