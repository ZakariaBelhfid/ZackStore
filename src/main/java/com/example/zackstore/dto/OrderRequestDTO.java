package com.example.zackstore.dto;

import com.example.zackstore.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {
    private Long userId;
    private List<OrderItemDTO> orderItems;
    private Double totalAmount;
    private String status;

    public void setStatus(Order.OrderStatus orderStatus) {
        this.status = orderStatus.toString();
    }
}