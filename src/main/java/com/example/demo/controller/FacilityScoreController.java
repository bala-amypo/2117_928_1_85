package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/facility-scores")
public class FacilityScoreController {

private final FacilityScoreService service;

public FacilityScoreController(FacilityScoreService service) { this.service = service; }

@PostMapping
public FacilityScore create(@RequestBody FacilityScore f) { return service.save(f); }

@GetMapping
public List<FacilityScore> getAll() { return service.getAll(); }

@GetMapping("/{id}")
public FacilityScore get(@PathVariable Long id) { return service.getById(id); }

@PutMapping("/{id}")
public FacilityScore update(@PathVariable Long id, @RequestBody FacilityScore f) { return service.update(id, f); }

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) { service.delete(id); }
}