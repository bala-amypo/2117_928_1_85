package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int schoolProximity;
    private int hospitalProximity;
    private int transportAccess;
    private int safetyScore;

    @OneToOne
    @JoinColumn(name = "property_id", unique = true)
    private Property property;

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSchoolProximity() {
        return schoolProximity;
    }

    public void setSchoolProximity(int schoolProximity) {
        this.schoolProximity = schoolProximity;
    }

    public int getHospitalProximity() {
        return hospitalProximity;
    }

    public void setHospitalProximity(int hospitalProximity) {
        this.hospitalProximity = hospitalProximity;
    }

    public int getTransportAccess() {
        return transportAccess;
    }

    public void setTransportAccess(int transportAccess) {
        this.transportAccess = transportAccess;
    }

    public int getSafetyScore() {
        return safetyScore;
    }

    public void setSafetyScore(int safetyScore) {
        this.safetyScore = safetyScore;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
