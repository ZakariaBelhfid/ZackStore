package com.example.zackstore.controller;

import com.example.zackstore.dto.OrderRequest;
import com.example.zackstore.model.Order;
import com.example.zackstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public Optional<Order> getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest orderRequest) {
        return orderService.updateOrder(orderId, orderRequest);
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }


}