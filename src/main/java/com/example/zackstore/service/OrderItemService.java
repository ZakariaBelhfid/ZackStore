package com.example.zackstore.service;
import com.example.zackstore.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.zackstore.model.OrderItem;
import java.util.List;
import java.util.Optional;
@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
