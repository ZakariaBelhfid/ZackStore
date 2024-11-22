package com.example.zackstore.mapper;

import com.example.zackstore.dto.OrderItemDTO;
import com.example.zackstore.model.Order;
import com.example.zackstore.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "order.orderId", target = "orderId")
    @Mapping(source = "product.productId", target = "productId")
    OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);

    @Mapping(source = "productId", target = "product.productId")
    @Mapping(target = "order", ignore = true)
    OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO);

    default Order mapOrder(Long orderId) {
        if (orderId == null) {
            return null;
        }
        Order order = new Order();
        order.setOrderId(orderId);
        return order;
    }
}