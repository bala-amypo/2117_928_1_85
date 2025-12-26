package com.example.demo.service;

import com.example.demo.entity.Property;
import com.example.demo.entity.RatingLog;
import com.example.demo.repository.RatingLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RatingLogService {
    
    @Autowired
    private RatingLogRepository ratingLogRepository;
    
    public RatingLog createLog(RatingLog ratingLog) {
        return ratingLogRepository.save(ratingLog);
    }
    
    public RatingLog logRatingActivity(Property property, String message) {
        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage(message);
        return ratingLogRepository.save(log);
    }
    
    public List<RatingLog> getAllLogs() {
        return ratingLogRepository.findAll();
    }
    
    public Optional<RatingLog> getLogById(Long id) {
        return ratingLogRepository.findById(id);
    }
    
    public List<RatingLog> getLogsByProperty(Property property) {
        return ratingLogRepository.findByProperty(property);
    }
    
    public void deleteLog(Long id) {
        ratingLogRepository.deleteById(id);
    }
}