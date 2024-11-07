package com.example.zackstore.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String email;

    @Getter
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    // Default constructor
    public User() {}

    // Parameterized constructor
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and setters
    public Long getUserId() {
        return id;
    }

    public void setUserId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}