package com.example.demo.service;

import com.example.demo.entity.Property;
import java.util.List;

public interface PropertyService {
Property save(Property property);
List<Property> getAll();
Property getById(Long id);
Property update(Long id, Property property);
void delete(Long id);
}