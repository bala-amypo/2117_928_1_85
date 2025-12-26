package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
    Optional<FacilityScore> findByProperty(Property property);
}
