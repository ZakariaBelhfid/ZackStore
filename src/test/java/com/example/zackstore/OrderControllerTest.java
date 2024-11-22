package com.example.zackstore;

import com.example.zackstore.dto.OrderItemDTO;
import com.example.zackstore.dto.OrderRequestDTO;
import com.example.zackstore.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.zackstore.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    void testCreateOrder() throws Exception {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(1L);
        orderItemDTO.setQuantity(2);
        orderItemDTO.setPrice(100.0);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setUserId(1L);
        orderRequestDTO.setOrderItems(Arrays.asList(orderItemDTO));
        orderRequestDTO.setTotalAmount(200.0);
        orderRequestDTO.setStatus(Order.OrderStatus.PENDING);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.totalAmount").value(200.0))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.orderItems[0].productId").value(1L))
                .andExpect(jsonPath("$.orderItems[0].quantity").value(2))
                .andExpect(jsonPath("$.orderItems[0].price").value(100.0));
    }
}