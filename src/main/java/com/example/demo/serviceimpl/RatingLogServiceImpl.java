package com.example.demo.serviceimpl;

import com.example.demo.entity.Property;
import com.example.demo.entity.RatingLog;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingLogRepository;
import com.example.demo.service.RatingLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingLogServiceImpl implements RatingLogService {

    private final RatingLogRepository ratingLogRepository;
    private final PropertyRepository propertyRepository;

    public RatingLogServiceImpl(RatingLogRepository ratingLogRepository,
                                PropertyRepository propertyRepository) {
        this.ratingLogRepository = ratingLogRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public void addLog(Long propertyId, String message) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage(message);

        ratingLogRepository.save(log);
    }

    @Override
    public List<RatingLog> getLogsByProperty(Long propertyId) {
        return ratingLogRepository.findByProperty_Id(propertyId);
    }
}
