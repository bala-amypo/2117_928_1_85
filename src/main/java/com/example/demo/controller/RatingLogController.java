package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rating-logs")
public class RatingLogController {

private final RatingLogService service;

public RatingLogController(RatingLogService service) { this.service = service; }

@PostMapping
public RatingLog create(@RequestBody RatingLog r) { return service.save(r); }

@GetMapping
public List<RatingLog> getAll() { return service.getAll(); }

@GetMapping("/{id}")
public RatingLog get(@PathVariable Long id) { return service.getById(id); }

@PutMapping("/{id}")
public RatingLog update(@PathVariable Long id, @RequestBody RatingLog r) { return service.update(id, r); }

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) { service.delete(id); }
}