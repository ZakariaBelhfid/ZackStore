package com.example.zackstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
}