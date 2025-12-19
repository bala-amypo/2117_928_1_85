package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FacilityScore;

@Repository
public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {

    Optional<FacilityScore> findByPropertyId(Long propertyId);
}
