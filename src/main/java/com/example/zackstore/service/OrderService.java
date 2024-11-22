package com.example.zackstore.service;

import com.example.zackstore.dto.OrderDTO;
import com.example.zackstore.dto.OrderRequestDTO;
import com.example.zackstore.mapper.OrderMapper;
import com.example.zackstore.model.Order;
import com.example.zackstore.model.OrderItem;
import com.example.zackstore.model.User;
import com.example.zackstore.repository.OrderRepository;
import com.example.zackstore.repository.OrderItemRepository;
import com.example.zackstore.repository.UserRepository;
import com.example.zackstore.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Transactional
    public OrderDTO createOrder(OrderRequestDTO orderRequestDTO) {
        // Fetch and set User
        User user = userRepository.findById(orderRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        // Create and save OrderItems
        List<OrderItem> orderItems = Optional.ofNullable(orderRequestDTO.getOrderItems())
                .orElse(Collections.emptyList())
                .stream()
                .map(orderItemDTO -> {
                    OrderItem orderItem = orderItemMapper.orderItemDTOToOrderItem(orderItemDTO);
                    return orderItemRepository.save(orderItem);
                })
                .collect(Collectors.toList());

        // Create and save Order
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalAmount(orderRequestDTO.getTotalAmount());
        order.setStatus(Order.OrderStatus.valueOf(orderRequestDTO.getStatus()));

        Order savedOrder = orderRepository.save(order);
        return orderMapper.orderToOrderDTO(savedOrder);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrderDTO> getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::orderToOrderDTO);
    }

    public OrderDTO updateOrder(Long orderId, OrderRequestDTO orderRequestDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));

        // Fetch and set User
        User user = userRepository.findById(orderRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        order.setUser(user);

        // Create and save OrderItems
        List<OrderItem> orderItems = Optional.ofNullable(orderRequestDTO.getOrderItems())
                .orElse(Collections.emptyList())
                .stream()
                .map(orderItemDTO -> {
                    OrderItem orderItem = orderItemMapper.orderItemDTOToOrderItem(orderItemDTO);
                    return orderItemRepository.save(orderItem);
                })
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);

        order.setTotalAmount(orderRequestDTO.getTotalAmount());
        order.setStatus(Order.OrderStatus.valueOf(orderRequestDTO.getStatus()));

        Order updatedOrder = orderRepository.save(order);
        return orderMapper.orderToOrderDTO(updatedOrder);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}