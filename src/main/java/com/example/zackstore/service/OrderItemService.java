package com.example.zackstore.service;

import com.example.zackstore.dto.OrderItemDTO;
import com.example.zackstore.mapper.OrderItemMapper;
import com.example.zackstore.model.OrderItem;
import com.example.zackstore.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(orderItemMapper::orderItemToOrderItemDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrderItemDTO> getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .map(orderItemMapper::orderItemToOrderItemDTO);
    }

    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemMapper.orderItemDTOToOrderItem(orderItemDTO);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.orderItemToOrderItemDTO(savedOrderItem);
    }

    public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}