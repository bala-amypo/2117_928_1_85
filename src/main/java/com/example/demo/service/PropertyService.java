package com.example.demo.service;

import com.example.demo.entity.Property;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.PropertyRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property addProperty(@Valid Property property) {
        if (property.getPrice() <= 0) {
            throw new BadRequestException("Invalid price");
        }
        return propertyRepository.save(property);
    }
}
