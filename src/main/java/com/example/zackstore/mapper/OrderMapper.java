package com.example.zackstore.mapper;

import com.example.zackstore.dto.OrderDTO;
import com.example.zackstore.dto.OrderRequestDTO;
import com.example.zackstore.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    OrderDTO orderToOrderDTO(Order order);
    Order orderRequestDTOToOrder(OrderRequestDTO orderRequestDTO);
}