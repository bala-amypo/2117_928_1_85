package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RatingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime loggedAt;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @PrePersist
    public void onCreate() {
        loggedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public Property getProperty() {
        return property;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
