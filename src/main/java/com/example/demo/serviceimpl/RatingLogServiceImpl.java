package com.example.demo.serviceimpl;

import com.example.demo.entity.RatingLog;
import com.example.demo.repository.RatingLogRepository;
import com.example.demo.service.RatingLogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RatingLogServiceImpl implements RatingLogService {

private final RatingLogRepository repo;

public RatingLogServiceImpl(RatingLogRepository repo) { this.repo = repo; }

public RatingLog save(RatingLog r) { return repo.save(r); }

public List<RatingLog> getAll() { return repo.findAll(); }

public RatingLog getById(Long id) { return repo.findById(id).orElse(null); }

public RatingLog update(Long id, RatingLog r) {
RatingLog rl = getById(id);
if (rl == null) return null;
rl.setPropertyId(r.getPropertyId());
rl.setMessage(r.getMessage());
rl.setLoggedAt(r.getLoggedAt());
return repo.save(rl);
}

public void delete(Long id) { repo.deleteById(id); }
}