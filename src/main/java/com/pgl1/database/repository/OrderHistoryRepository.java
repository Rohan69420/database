package com.pgl1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgl1.database.model.entity.OrderHistory;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
    List<OrderHistory> findByOrderHistoryId(Long orderHistoryId);
}
