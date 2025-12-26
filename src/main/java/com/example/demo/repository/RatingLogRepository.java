package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {
    List<RatingLog> findByProperty(Property property);
}
