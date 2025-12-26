package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
public class RatingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime loggedAt = LocalDateTime.now();

    @ManyToOne
    private Property property;

    public void setMessage(String message) { this.message = message; }
    public void setProperty(Property property) { this.property = property; }
    public LocalDateTime getLoggedAt() { return loggedAt; }
}
