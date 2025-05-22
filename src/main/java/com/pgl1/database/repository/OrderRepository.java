package com.pgl1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgl1.database.model.entity.Order;
import com.pgl1.database.model.entity.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findById(Long id);

    List<Order> findByUserAndSourceAndDestination(User user);

    List<Order> findByUserAndStatus(User user, String status);
}
