package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "property_id"))
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Min(0) @Max(10)
    private int schoolProximity;
    @Min(0) @Max(10)
    private int hospitalProximity;
    @Min(0) @Max(10)
    private int transportAccess;
    @Min(0) @Max(10)
    private int safetyScore;

    public Long getId() { return id; }
    public Property getProperty() { return property; }
    public int getSchoolProximity() { return schoolProximity; }

    public void setProperty(Property property) { this.property = property; }
    public void setSchoolProximity(int v) { this.schoolProximity = v; }
    public void setHospitalProximity(int v) { this.hospitalProximity = v; }
    public void setTransportAccess(int v) { this.transportAccess = v; }
    public void setSafetyScore(int v) { this.safetyScore = v; }
}
