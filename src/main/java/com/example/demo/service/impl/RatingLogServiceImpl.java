package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RatingLogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RatingLogServiceImpl implements RatingLogService {

    private final RatingLogRepository logRepository;
    private final PropertyRepository propertyRepository;

    public RatingLogServiceImpl(RatingLogRepository logRepository, PropertyRepository propertyRepository) {
        this.logRepository = logRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public RatingLog addLog(Long propertyId, String message) {
        RatingLog log = new RatingLog();
        log.setProperty(propertyRepository.findById(propertyId).orElseThrow());
        log.setMessage(message);
        return logRepository.save(log);
    }

    @Override
    public List<RatingLog> getLogs(Long propertyId) {
        return logRepository.findByPropertyId(propertyId);
    }
}
