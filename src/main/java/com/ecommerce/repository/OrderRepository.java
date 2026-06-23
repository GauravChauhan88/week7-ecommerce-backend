package com.ecommerce.repository;

import com.ecommerce.model.entity.Order;
import com.ecommerce.model.entity.User;
import com.ecommerce.model.enums.OrderStatus; // <-- Pointing to enums package
import com.ecommerce.model.dto.OrderSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    Page<Order> findByUser(User user, Pageable pageable);
    
    List<Order> findByStatus(OrderStatus status);
    
    @Query("SELECT o FROM Order o WHERE o.createdAt BETWEEN :startDate AND :endDate")
    List<Order> findOrdersBetweenDates(@Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.user WHERE o.orderNumber = :orderNumber")
    Optional<Order> findByOrderNumberWithUser(@Param("orderNumber") String orderNumber);
    
    @Query("SELECT new com.ecommerce.model.dto.OrderSummaryDTO(o.id, o.orderNumber, o.totalAmount, o.status, o.createdAt) " +
           "FROM Order o WHERE o.user.id = :userId")
    List<OrderSummaryDTO> findOrderSummariesByUserId(@Param("userId") Long userId);
    
    @Query(value = """
        SELECT o.created_at\\:\\:date as order_date,
               COUNT(*) as total_orders,
               SUM(o.total_amount) as total_revenue
        FROM orders o
        WHERE o.created_at >= :startDate
        GROUP BY o.created_at\\:\\:date
        ORDER BY order_date DESC
        """, nativeQuery = true)
    List<Object[]> getDailyOrderReport(@Param("startDate") LocalDateTime startDate);
}