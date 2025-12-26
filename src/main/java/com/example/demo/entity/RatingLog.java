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

    public void setMessage(String m) { this.message = m; }
    public void setProperty(Property p) { this.property = p; }
    public LocalDateTime getLoggedAt() { return loggedAt; }
}
