package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.*;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @Min(1)
    private Double price;

    @Min(100)
    private Double areaSqFt;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private FacilityScore facilityScore;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private RatingResult ratingResult;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingLog> ratingLogs = new ArrayList<>();

    @ManyToMany(mappedBy = "assignedProperties")
    private Set<User> assignedUsers = new HashSet<>();

    public void addRatingLog(RatingLog log) {
        log.setProperty(this);
        this.ratingLogs.add(log);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public Double getPrice() {
        return price;
    }

    public Double getAreaSqFt() {
        return areaSqFt;
    }

    public FacilityScore getFacilityScore() {
        return facilityScore;
    }

    public RatingResult getRatingResult() {
        return ratingResult;
    }

    public List<RatingLog> getRatingLogs() {
        return ratingLogs;
    }

    public Set<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAreaSqFt(Double areaSqFt) {
        this.areaSqFt = areaSqFt;
    }

    public void setFacilityScore(FacilityScore facilityScore) {
        this.facilityScore = facilityScore;
    }

    public void setRatingResult(RatingResult ratingResult) {
        this.ratingResult = ratingResult;
    }

    public void setRatingLogs(List<RatingLog> ratingLogs) {
        this.ratingLogs = ratingLogs;
    }

    public void setAssignedUsers(Set<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }
}
