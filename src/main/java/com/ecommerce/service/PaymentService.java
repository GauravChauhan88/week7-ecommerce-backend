package com.ecommerce.service;

import com.ecommerce.model.entity.Order;
import com.ecommerce.model.entity.Payment;
import com.ecommerce.model.enums.PaymentStatus;
import com.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public Payment processOrderPayment(Order order, String method) {
        // Mock processing logic
        Payment payment = Payment.builder()
                .order(order)
                .amount(order.getTotalAmount())
                .method(method)
                .status(PaymentStatus.COMPLETED)
                .transactionId("TXN-" + UUID.randomUUID().toString().toUpperCase().substring(0, 8))
                .build();
        return paymentRepository.save(payment);
    }
}