package com.example.zackstore.service;

import com.example.zackstore.dto.ProductDTO;
import com.example.zackstore.model.Product;
import com.example.zackstore.repository.ProductRepository;
import com.example.zackstore.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getAllProductDTOs() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductDTOById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDTO);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        product = productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}