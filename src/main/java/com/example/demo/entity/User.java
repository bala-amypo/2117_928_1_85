package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role;

    @ManyToMany
    private Set<Property> assignedProperties = new HashSet<>();

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public Set<Property> getAssignedProperties() { return assignedProperties; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
    public void setAssignedProperties(Set<Property> assignedProperties) { this.assignedProperties = assignedProperties; }
}
