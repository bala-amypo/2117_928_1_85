package com.example.demo.service;

import com.example.demo.entity.RatingResult;
import java.util.List;

public interface RatingResultService {
RatingResult save(RatingResult r);
List<RatingResult> getAll();
RatingResult getById(Long id);
RatingResult update(Long id, RatingResult r);
void delete(Long id);
}