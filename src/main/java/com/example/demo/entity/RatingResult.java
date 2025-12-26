package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double finalRating;
    private String ratingCategory;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private LocalDateTime ratedAt = LocalDateTime.now();

    public Long getId() { return id; }
    public Double getFinalRating() { return finalRating; }
    public String getRatingCategory() { return ratingCategory; }
    public LocalDateTime getRatedAt() { return ratedAt; }

    public void setProperty(Property p) { this.property = p; }
    public void setFinalRating(Double r) { this.finalRating = r; }
    public void setRatingCategory(String c) { this.ratingCategory = c; }
}
