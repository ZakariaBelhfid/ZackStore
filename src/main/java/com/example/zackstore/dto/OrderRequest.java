package com.example.zackstore.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {
    // Getters and setters
    private Long userId;
    private List<Long> productIds;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}