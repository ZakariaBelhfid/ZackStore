package com.example.zackstore;

import com.example.zackstore.dto.OrderItemDTO;
import com.example.zackstore.mapper.OrderItemMapper;
import com.example.zackstore.model.OrderItem;
import com.example.zackstore.model.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemMapperTest {

    private OrderItemMapper orderItemMapper = Mappers.getMapper(OrderItemMapper.class);

    @Test
    void testOrderItemToOrderItemDTO_NullOrder() {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(new Product(1L));
        orderItem.setQuantity(2);
        orderItem.setPrice(100.0);

        OrderItemDTO orderItemDTO = orderItemMapper.orderItemToOrderItemDTO(orderItem);

        assertNull(orderItemDTO.getOrderId());
        assertEquals(1L, orderItemDTO.getProductId());
        assertEquals(2, orderItemDTO.getQuantity());
        assertEquals(100.0, orderItemDTO.getPrice());
    }

    @Test
    void testOrderItemDTOToOrderItem_NullOrderId() {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(1L);
        orderItemDTO.setQuantity(2);
        orderItemDTO.setPrice(100.0);

        OrderItem orderItem = orderItemMapper.orderItemDTOToOrderItem(orderItemDTO);

        assertNull(orderItem.getOrder());
        assertEquals(1L, orderItem.getProduct().getProductId());
        assertEquals(2, orderItem.getQuantity());
        assertEquals(100.0, orderItem.getPrice());
    }
}