package com.example.zackstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private Long orderId;
    private Long orderItemId;
    private Long productId;
    private Integer quantity;
    private Double price;
}