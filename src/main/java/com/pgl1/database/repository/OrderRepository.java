package com.pgl1.database.repository;

import com.pgl1.database.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderId(Integer orderId);
}
