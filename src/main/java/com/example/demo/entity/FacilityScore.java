package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double hospitalProximity;
    private double transportAccess;
    private double safetyScore;

    @ManyToOne
    private Property property;

    // ===== getters & setters =====
    public Long getId() {
        return id;
    }

    public double getHospitalProximity() {
        return hospitalProximity;
    }

    public void setHospitalProximity(double hospitalProximity) {
        this.hospitalProximity = hospitalProximity;
    }

    public double getTransportAccess() {
        return transportAccess;
    }

    public void setTransportAccess(double transportAccess) {
        this.transportAccess = transportAccess;
    }

    public double getSafetyScore() {
        return safetyScore;
    }

    public void setSafetyScore(double safetyScore) {
        this.safetyScore = safetyScore;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
