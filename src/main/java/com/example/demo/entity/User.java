package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

@Column(unique = true)
private String email;

private String password;

private String role = "ANALYST";

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getName() { return name; }
public void setName(String name) { this.name = name; }

public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }

public String getPassword() { return password; }
public void setPassword(String password) { this.password = password; }

public String getRole() { return role; }
public void setRole(String role) { this.role = role; }
}




package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.util.List;

@Entity
public class Property {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String title;
private String address;
private String city;

@Min(1)
private Double price;

@Min(100)
private Double areaSqFt;

@OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
private List<RatingLog> ratingLogs;

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }

public String getAddress() { return address; }
public void setAddress(String address) { this.address = address; }

public String getCity() { return city; }
public void setCity(String city) { this.city = city; }

public Double getPrice() { return price; }
public void setPrice(Double price) { this.price = price; }

public Double getAreaSqFt() { return areaSqFt; }
public void setAreaSqFt(Double areaSqFt) { this.areaSqFt = areaSqFt; }
}



package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;

@Entity
public class FacilityScore {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
private Property property;

@Max(10)
private Integer schoolProximity;

@Max(10)
private Integer hospitalProximity;

@Max(10)
private Integer transportAccess;

@Max(10)
private Integer safetyScore;

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public Property getProperty() { return property; }
public void setProperty(Property property) { this.property = property; }

public Integer getSchoolProximity() { return schoolProximity; }
public void setSchoolProximity(Integer schoolProximity) { this.schoolProximity = schoolProximity; }

public Integer getHospitalProximity() { return hospitalProximity; }
public void setHospitalProximity(Integer hospitalProximity) { this.hospitalProximity = hospitalProximity; }

public Integer getTransportAccess() { return transportAccess; }
public void setTransportAccess(Integer transportAccess) { this.transportAccess = transportAccess; }

public Integer getSafetyScore() { return safetyScore; }
public void setSafetyScore(Integer safetyScore) { this.safetyScore = safetyScore; }
}


package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingResult {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@OneToOne
private Property property;

private Double finalRating;
private String ratingCategory;

private LocalDateTime ratedAt;

@PrePersist
public void onCreate() {
ratedAt = LocalDateTime.now();
}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public Property getProperty() { return property; }
public void setProperty(Property property) { this.property = property; }

public Double getFinalRating() { return finalRating; }
public void setFinalRating(Double finalRating) { this.finalRating = finalRating; }

public String getRatingCategory() { return ratingCategory; }
public void setRatingCategory(String ratingCategory) { this.ratingCategory = ratingCategory; }

public LocalDateTime getRatedAt() { return ratedAt; }
}



package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingLog {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
private Property property;

private String message;

private LocalDateTime loggedAt;

@PrePersist
public void onCreate() {
loggedAt = LocalDateTime.now();
}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public Property getProperty() { return property; }
public void setProperty(Property property) { this.property = property; }

public String getMessage() { return message; }
public void setMessage(String message) { this.message = message; }

public LocalDateTime getLoggedAt() { return loggedAt; }
}


