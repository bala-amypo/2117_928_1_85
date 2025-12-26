package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {
    Optional<RatingResult> findByProperty(Property property);
}
