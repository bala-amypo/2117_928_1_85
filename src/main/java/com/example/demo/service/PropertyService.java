package com.example.demo.service;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public Property addProperty(Property property) {
        return repository.save(property);
    }
}
