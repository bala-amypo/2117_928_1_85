package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import java.util.List;

public interface FacilityScoreService {
FacilityScore save(FacilityScore f);
List<FacilityScore> getAll();
FacilityScore getById(Long id);
FacilityScore update(Long id, FacilityScore f);
void delete(Long id);
}