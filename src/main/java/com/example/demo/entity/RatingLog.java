package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingLog {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Property property;

    private String message;
    private LocalDateTime loggedAt = LocalDateTime.now();

    public Long getId() { return id; }
    public Property getProperty() { return property; }
    public String getMessage() { return message; }
    public LocalDateTime getLoggedAt() { return loggedAt; }

    public void setProperty(Property property) { this.property = property; }
    public void setMessage(String message) { this.message = message; }
}
