package com.example.zackstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Double amount;
    private Date paymentDate;
    private String status;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}