package com.pgl1.database.repository;

import com.pgl1.database.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pgl1.database.model.entity.OrderHistory;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    List<OrderHistory> findByOrderId(Long orderId);
    List<OrderHistory> findByOrderId(Long orderId, Sort sort);
    Page<OrderHistory> findByOrderId(Long orderId, Pageable pageable);
    Page<OrderHistory> findByOrderIdAndNewStatus(Long orderId, OrderStatus status, Pageable pageable);
}
