package com.example.demo.service.impl;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository repository;

    public PropertyServiceImpl(PropertyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Property addProperty(Property property) {
        return repository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return repository.findAll();
    }
}
