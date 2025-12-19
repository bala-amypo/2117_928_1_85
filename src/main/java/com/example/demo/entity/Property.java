package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String address;
    private String city;
    private Double price;
    private Double areaSqft;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingLog> ratingLogs;

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public Double getPrice() {
        return price;
    }
 
    public void setPrice(Double price) {
        this.price = price;
    }
 
    public Double getAreaSqft() {
        return areaSqft;
    }
 
    public void setAreaSqft(Double areaSqft) {
        this.areaSqft = areaSqft;
    }
 
    public List<RatingLog> getRatingLogs() {
        return ratingLogs;
    }
 
    public void setRatingLogs(List<RatingLog> ratingLogs) {
        this.ratingLogs = ratingLogs;
    }
}
