package com.example.zackstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long orderId;
    private Long userId;
    private List<OrderItemDTO> orderItems;
    private Double totalAmount;
    private String status;
}