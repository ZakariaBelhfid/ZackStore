package com.example.zackstore.mapper;

import com.example.zackstore.dto.ProductDTO;
import com.example.zackstore.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
}