package com.example.zackstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public void setUserId(Long userId) {
        if (this.user == null) {
            this.user = new User();
        }
        this.user.setUserId(userId);
    }

    public enum OrderStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        CANCELLED
    }

    public static Order fromOrderId(Long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        return order;
    }
}