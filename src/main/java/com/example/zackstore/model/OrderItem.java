package com.example.zackstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @Getter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Getter
    private int quantity;

    // Getters and setters
    public Long getId() {
        return orderItemId;
    }

    public void setId(Long OrderItemId) {
        this.orderItemId = OrderItemId;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return product.getId();
    }
}