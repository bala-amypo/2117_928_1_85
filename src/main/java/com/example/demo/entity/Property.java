package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String address;
    private String city;
    private Double price;
    private Double areaSqFt;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private FacilityScore facilityScore;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private RatingResult ratingResult;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingLog> ratingLogs = new ArrayList<>();

    @ManyToMany(mappedBy = "assignedProperties")
    private Set<User> assignedUsers = new HashSet<>();

    public void addRatingLog(RatingLog log) {
        ratingLogs.add(log);
        log.setProperty(this);
    }

    public Long getId() { return id; }
    public String getCity() { return city; }
    public Double getPrice() { return price; }
    public Set<User> getAssignedUsers() { return assignedUsers; }

    public void setTitle(String t) { this.title = t; }
    public void setAddress(String a) { this.address = a; }
    public void setCity(String c) { this.city = c; }
    public void setPrice(Double p) { this.price = p; }
    public void setAreaSqFt(Double a) { this.areaSqFt = a; }
}
