package com.ecommerce.controller;

import com.ecommerce.model.dto.OrderDTO;
import com.ecommerce.model.entity.Order;
import com.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam Long userId, @RequestBody OrderDTO orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(userId, orderRequest));
    }
}