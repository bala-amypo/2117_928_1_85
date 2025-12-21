package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Property property;

    private Double rating;
    private String category;

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public Property getProperty() {
        return property;
    }
 
    public void setProperty(Property property) {
        this.property = property;
    }
 
    public Double getRating() {
        return rating;
    }
 
    public void setRating(Double rating) {
        this.rating = rating;
    }
 
    public String getCategory() {
        return category;
    }
 
    public void setCategory(String category) {
        this.category = category;
    }
}
