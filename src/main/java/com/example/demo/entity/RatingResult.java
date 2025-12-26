package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    private double finalRating;

    private String ratingCategory;

    private LocalDateTime ratedAt;

    @PrePersist
    public void onCreate() {
        ratedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public double getFinalRating() {
        return finalRating;
    }

    public String getRatingCategory() {
        return ratingCategory;
    }

    public LocalDateTime getRatedAt() {
        return ratedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setFinalRating(double finalRating) {
        this.finalRating = finalRating;
    }

    public void setRatingCategory(String ratingCategory) {
        this.ratingCategory = ratingCategory;
    }

    public void setRatedAt(LocalDateTime ratedAt) {
        this.ratedAt = ratedAt;
    }
}
