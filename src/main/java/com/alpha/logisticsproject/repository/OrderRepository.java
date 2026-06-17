package com.alpha.logisticsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alpha.logisticsproject.entity.Order;
import com.alpha.logisticsproject.enums.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    // Optimized signature to count data records safely using the status enum definitions
    long countByStatus(OrderStatus status);

}