package com.example.demo.serviceimpl;

import com.example.demo.entity.RatingResult;
import com.example.demo.repository.RatingResultRepository;
import com.example.demo.service.RatingResultService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RatingResultServiceImpl implements RatingResultService {

private final RatingResultRepository repo;

public RatingResultServiceImpl(RatingResultRepository repo) { this.repo = repo; }

public RatingResult save(RatingResult r) { return repo.save(r); }

public List<RatingResult> getAll() { return repo.findAll(); }

public RatingResult getById(Long id) { return repo.findById(id).orElse(null); }

public RatingResult update(Long id, RatingResult r) {
RatingResult rr = getById(id);
if (rr == null) return null;
rr.setFinalRating(r.getFinalRating());
rr.setRatingCategory(r.getRatingCategory());
rr.setRatedAt(r.getRatedAt());
return repo.save(rr);
}

public void delete(Long id) { repo.deleteById(id); }
}