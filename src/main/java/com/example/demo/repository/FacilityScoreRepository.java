package com.example.demo.repository;

import com.example.demo.entity.FacilityScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
Optional<FacilityScore> findByPropertyId(Long propertyId);
}

package com.example.demo.repository;

import com.example.demo.entity.RatingLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {
List<RatingLog> findByPropertyId(Long propertyId);
}


package com.example.demo.repository;

import com.example.demo.entity.RatingResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {
}


package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
Optional<User> findByEmail(String email);
}