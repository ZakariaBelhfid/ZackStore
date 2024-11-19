package com.example.zackstore.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {
    private Long userId;
    private List<Long> productIds;
    @Getter
    private Double totalAmount;
    @Getter
    private String status;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}