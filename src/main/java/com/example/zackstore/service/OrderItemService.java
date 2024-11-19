package com.example.zackstore.service;

import com.example.zackstore.model.Order;
import com.example.zackstore.model.OrderItem;
import com.example.zackstore.repository.OrderItemRepository;
import com.example.zackstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        Order order = orderItem.getOrder();
        if (order != null && order.getOrderId() == null) {
            order = orderRepository.save(order);
            orderItem.setOrder(order);
        }
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}