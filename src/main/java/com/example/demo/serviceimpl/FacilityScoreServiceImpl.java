package com.example.demo.serviceimpl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.service.FacilityScoreService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

private final FacilityScoreRepository repo;

public FacilityScoreServiceImpl(FacilityScoreRepository repo) { this.repo = repo; }

public FacilityScore save(FacilityScore f) { return repo.save(f); }

public List<FacilityScore> getAll() { return repo.findAll(); }

public FacilityScore getById(Long id) { return repo.findById(id).orElse(null); }

public FacilityScore update(Long id, FacilityScore f) {
FacilityScore fs = getById(id);
if (fs == null) return null;
fs.setSchoolProximity(f.getSchoolProximity());
fs.setHospitalProximity(f.getHospitalProximity());
fs.setTransportAccess(f.getTransportAccess());
fs.setSafetyScore(f.getSafetyScore());
return repo.save(fs);
}

public void delete(Long id) { repo.deleteById(id); }
}
