package com.example.zackstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    // Constructor with only productId
    public Product(Long productId) {
        this.productId = productId;
    }
}