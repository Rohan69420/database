package com.pgl1.database.repository;

import com.pgl1.database.model.entity.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pgl1.database.model.entity.Order;
import com.pgl1.database.model.entity.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserAndSourceAndDestination(User user, PickupPoint source, PickupPoint destination);

    List<Order> findByUserAndStatus(User user, String status);
}
