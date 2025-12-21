package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "properties")
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

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private FacilityScore facilityScore;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private RatingResult ratingResult;

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
 
    public Double getAreaSqFt() {
        return areaSqFt;
    }
 
    public void setAreaSqFt(Double areaSqFt) {
        this.areaSqFt = areaSqFt;
    }
}
