package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Property {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String address;
    private String city;
    private Double price;
    private Double areaSqFt;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RatingLog> ratingLogs = new HashSet<>();

    @ManyToMany(mappedBy = "assignedProperties")
    private Set<User> assignedUsers = new HashSet<>();

    public void addRatingLog(RatingLog log) {
        log.setProperty(this);
        ratingLogs.add(log);
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public Double getPrice() { return price; }
    public Double getAreaSqFt() { return areaSqFt; }
    public Set<User> getAssignedUsers() { return assignedUsers; }

    public void setTitle(String title) { this.title = title; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setPrice(Double price) { this.price = price; }
    public void setAreaSqFt(Double areaSqFt) { this.areaSqFt = areaSqFt; }
}
