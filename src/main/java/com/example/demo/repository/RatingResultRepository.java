package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.RatingResult;

@Repository
public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {

    Optional<RatingResult> findByPropertyId(Long propertyId);
}
