package com.example.zackstore.service;

import com.example.zackstore.dto.OrderRequest;
import com.example.zackstore.model.Order;
import com.example.zackstore.model.OrderItem;
import com.example.zackstore.model.Product;
import com.example.zackstore.model.User;
import com.example.zackstore.repository.OrderRepository;
import com.example.zackstore.repository.ProductRepository;
import com.example.zackstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        User user = getUser(orderRequest.getUserId());
        List<Product> products = getProducts(orderRequest.getProductIds());

        Order order = buildOrder(user, products);
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(Long orderId, OrderRequest orderRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        User user = getUser(orderRequest.getUserId());
        List<Product> products = getProducts(orderRequest.getProductIds());

        order.setUser(user);
        order.setOrderItems(products.stream().map(product -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList()));
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setStatus(orderRequest.getStatus());

        return orderRepository.save(order);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    private List<Product> getProducts(List<Long> productIds) {
        return productIds.stream()
                .map(productId -> productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found")))
                .collect(Collectors.toList());
    }

    private Order buildOrder(User user, List<Product> products) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(products.stream().map(product -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList()));
        return order;
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }
}