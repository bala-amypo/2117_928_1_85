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