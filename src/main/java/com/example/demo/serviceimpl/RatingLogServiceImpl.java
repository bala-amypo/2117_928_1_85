package com.example.demo.serviceimpl;

import com.example.demo.entity.RatingLog;
import com.example.demo.repository.RatingLogRepository;
import com.example.demo.service.RatingLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingLogServiceImpl implements RatingLogService {

    private final RatingLogRepository repository;

    public RatingLogServiceImpl(RatingLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public RatingLog save(RatingLog log) {
        log.setLoggedAt(LocalDateTime.now());
        return repository.save(log);
    }

    @Override
    public List<RatingLog> getAll() {
        return repository.findAll();
    }

    @Override
    public RatingLog getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("RatingLog not found"));
    }

    @Override
    public RatingLog update(Long id, RatingLog log) {
        RatingLog existing = getById(id);
        existing.setMessage(log.getMessage());
        existing.setProperty(log.getProperty());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
