package com.example.zackstore.controller;

import com.example.zackstore.dto.OrderItemDTO;
import com.example.zackstore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{orderItemId}")
    public OrderItemDTO getOrderItemById(@PathVariable Long orderItemId) {
        return orderItemService.getOrderItemById(orderItemId).orElse(null);
    }

    @PostMapping
    public OrderItemDTO createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        return orderItemService.createOrderItem(orderItemDTO);
    }

    @DeleteMapping("/{orderItemId}")
    public void deleteOrderItem(@PathVariable Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
    }
}