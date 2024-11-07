package com.example.zackstore;

import com.example.zackstore.model.Order;
import com.example.zackstore.model.User;
import com.example.zackstore.model.Product;
import com.example.zackstore.repository.OrderRepository;
import com.example.zackstore.repository.UserRepository;
import com.example.zackstore.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void testCreateOrder() throws Exception {
        User user = createUser();
        Product product = createProduct();

        String orderJson = createOrderJson(user.getUserId(), product.getProductId());

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk());

        verifyOrderSaved(user, product);
    }

    private User createUser() {
        User user = new User();
        user.setUsername("John Doe");
        user.setEmail("john.doe@gmail.com");
        userRepository.save(user);
        return user;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setName("Product 1");
        product.setDescription("Iphone 12");
        product.setPrice(1200.0);
        productRepository.save(product);
        return product;
    }

    private String createOrderJson(Long userId, Long productId) {
        return "{ \"userId\": " + userId + ", \"productIds\": [ " + productId + " ] }";
    }

    private void verifyOrderSaved(User user, Product product) {
        Order savedOrder = orderRepository.findAll().get(0);
        assertThat(savedOrder.getUser().getUserId()).isEqualTo(user.getUserId());
        assertThat(savedOrder.getOrderItems().get(0).getProduct().getProductId()).isEqualTo(product.getProductId());
    }

    @Test
    @Transactional
    public void testCreateOrderWithInvalidUserId() throws Exception {
        String orderJson = createOrderJson(999L, createProduct().getProductId());

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testCreateOrderWithInvalidProductId() throws Exception {
        User user = createUser();
        String orderJson = createOrderJson(user.getUserId(), 999L);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testCreateOrderWithEmptyProductList() throws Exception {
        User user = createUser();
        String orderJson = "{ \"userId\": " + user.getUserId() + ", \"productIds\": [] }";

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testCreateOrderWithMissingFields() throws Exception {
        String orderJson = "{ \"userId\": null, \"productIds\": [ 1 ] }";

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testCreateOrderWithDuplicateProductIds() throws Exception {
        User user = createUser();
        Product product = createProduct();
        String orderJson = "{ \"userId\": " + user.getUserId() + ", \"productIds\": [ " + product.getProductId() + ", " + product.getProductId() + " ] }";

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk());

        verifyOrderSaved(user, product);
    }
}