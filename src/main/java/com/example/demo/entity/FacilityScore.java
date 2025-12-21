package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Property property;

    @Min(0) @Max(10)
    private Integer school;

    @Min(0) @Max(10)
    private Integer hospital;

    @Min(0) @Max(10)
    private Integer transport;

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
 
    public Integer getSchool() {
        return school;
    }
 
    public void setSchool(Integer school) {
        this.school = school;
    }
 
    public Integer getHospital() {
        return hospital;
    }
 
    public void setHospital(Integer hospital) {
        this.hospital = hospital;
    }
 
    public Integer getTransport() {
        return transport;
    }
 
    public void setTransport(Integer transport) {
        this.transport = transport;
    }
}
