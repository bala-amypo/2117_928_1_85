package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingResultService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rating-results")
public class RatingResultController {

private final RatingResultService service;

public RatingResultController(RatingResultService service) { this.service = service; }

@PostMapping
public RatingResult create(@RequestBody RatingResult r) { return service.save(r); }

@GetMapping
public List<RatingResult> getAll() { return service.getAll(); }

@GetMapping("/{id}")
public RatingResult get(@PathVariable Long id) { return service.getById(id); }

@PutMapping("/{id}")
public RatingResult update(@PathVariable Long id, @RequestBody RatingResult r) { return service.update(id, r); }

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) { service.delete(id); }
}