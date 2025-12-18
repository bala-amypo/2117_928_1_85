package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.service.PropertyService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

private final PropertyService service;

public PropertyController(PropertyService service) { this.service = service; }

@PostMapping
public Property create(@RequestBody Property property) { return service.save(property); }

@GetMapping
public List<Property> getAll() { return service.getAll(); }

@GetMapping("/{id}")
public Property get(@PathVariable Long id) { return service.getById(id); }

@PutMapping("/{id}")
public Property update(@PathVariable Long id, @RequestBody Property property) { return service.update(id, property); }

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) { service.delete(id); }
}