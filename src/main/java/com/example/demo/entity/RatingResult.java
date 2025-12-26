package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingResult {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Property property;

    private Double finalRating;
    private String ratingCategory;
    private LocalDateTime ratedAt = LocalDateTime.now();

    public Long getId() { return id; }
    public Property getProperty() { return property; }
    public Double getFinalRating() { return finalRating; }
    public String getRatingCategory() { return ratingCategory; }
    public LocalDateTime getRatedAt() { return ratedAt; }

    public void setProperty(Property property) { this.property = property; }
    public void setFinalRating(Double finalRating) { this.finalRating = finalRating; }
    public void setRatingCategory(String ratingCategory) { this.ratingCategory = ratingCategory; }
}
