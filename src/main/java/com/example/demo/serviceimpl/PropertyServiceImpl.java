package com.example.demo.serviceimpl;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

private final PropertyRepository repo;

public PropertyServiceImpl(PropertyRepository repo) { this.repo = repo; }

public Property save(Property property) { return repo.save(property); }

public List<Property> getAll() { return repo.findAll(); }

public Property getById(Long id) { return repo.findById(id).orElse(null); }

public Property update(Long id, Property property) {
Property p = getById(id);
if (p == null) return null;
p.setTitle(property.getTitle());
p.setCity(property.getCity());
p.setPrice(property.getPrice());
return repo.save(p);
}

public void delete(Long id) { repo.deleteById(id); }
}