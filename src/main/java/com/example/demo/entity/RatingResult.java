package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingResult {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long propertyId;
private Double finalRating;
private String ratingCategory;
private LocalDateTime ratedAt;

public Long getId() {
return id;
}

public Long getPropertyId() {
return propertyId;
}

public void setPropertyId(Long propertyId) {
this.propertyId = propertyId;
}

public Double getFinalRating() {
return finalRating;
}

public void setFinalRating(Double finalRating) {
this.finalRating = finalRating;
}

public String getRatingCategory() {
return ratingCategory;
}

public void setRatingCategory(String ratingCategory) {
this.ratingCategory = ratingCategory;
}

public LocalDateTime getRatedAt() {
return ratedAt;
}

public void setRatedAt(LocalDateTime ratedAt) {
this.ratedAt = ratedAt;
}
}