package com.ecommerce.service;

import com.ecommerce.exception.InsufficientStockException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.entity.*;
import com.ecommerce.model.enums.OrderStatus;
import com.ecommerce.model.dto.OrderDTO;
import com.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;
    private final PaymentService paymentService;

    @Transactional
    public Order createOrder(Long userId, OrderDTO orderRequest) {
        User user = userService.getUserById(userId);
        
        Order order = Order.builder()
                .orderNumber("ORD-" + UUID.randomUUID().toString().toUpperCase().substring(0, 10))
                .user(user)
                .status(OrderStatus.PENDING)
                .shippingAddress(orderRequest.getShippingAddress())
                .build();

        for (OrderDTO.OrderItemDTO itemDTO : orderRequest.getItems()) {
            Product product = productService.getProductById(itemDTO.getProductId());
            
            if (product.getStock() < itemDTO.getQuantity()) {
                throw new InsufficientStockException("Product " + product.getName() + " does not have enough stock available.");
            }
            
            product.decreaseStock(itemDTO.getQuantity());
            
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemDTO.getQuantity())
                    .price(product.getPrice())
                    .build();
                    
            order.addOrderItem(orderItem);
        }

        order.setTotalAmount(order.calculateTotal());
        Order savedOrder = orderRepository.save(order);
        
        // Instant Payment Processing Hook
        Payment payment = paymentService.processOrderPayment(savedOrder, "CREDIT_CARD");
        savedOrder.setPayment(payment);
        savedOrder.setStatus(OrderStatus.PAID);
        
        return orderRepository.save(savedOrder);
    }
}