package com.example.demo.service;

import com.example.demo.entity.RatingLog;
import java.util.List;

public interface RatingLogService {
RatingLog save(RatingLog r);
List<RatingLog> getAll();
RatingLog getById(Long id);
RatingLog update(Long id, RatingLog r);
void delete(Long id);
}