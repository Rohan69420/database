package com.pgl1.database.service;

import com.pgl1.database.enums.ChangeType;
import com.pgl1.database.enums.OrderStatus;
import com.pgl1.database.model.entity.Order;
import com.pgl1.database.model.entity.OrderHistory;
import com.pgl1.database.repository.OrderHistoryRepository;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;

    @Transactional
    public void recordOrderCreation(Order order) {
        OrderHistory history = OrderHistory.builder()
                .order(order)
                .changeType(ChangeType.CREATED)
                .previousStatus(null)
                .newStatus(order.getStatus())
                .build();

        orderHistoryRepository.save(history);
        log.info("Recorded order creation for order ID: {}", order.getId());
    }

    @Transactional
    public void recordOrderUpdate(Order previousState, Order newState) {
        if (!previousState.getStatus().equals(newState.getStatus())) {
            OrderHistory history = OrderHistory.builder()
                    .order(newState)
                    .changeType(ChangeType.STATUS_CHANGED)
                    .previousStatus(previousState.getStatus())
                    .newStatus(newState.getStatus())
                    .build();

            orderHistoryRepository.save(history);
            log.info("Recorded status change for order ID: {}", newState.getId());
        }
        else {

            // Record general updates (non-status changes)
            OrderHistory updateHistory = OrderHistory.builder()
                    .order(newState)
                    .changeType(ChangeType.UPDATED)
                    .previousStatus(previousState.getStatus())
                    .newStatus(newState.getStatus())
                    .build();

            orderHistoryRepository.save(updateHistory);
            log.info("Recorded order update for order ID: {}", newState.getId());
        }

    }

    public List<OrderHistory> fetchOrderHistoryByOrderId(Long orderId, Integer page){
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page,10,sort);
        return orderHistoryRepository.findByOrderId(orderId, pageable).getContent();
    }

    public List<OrderHistory> fetchOrderHistoryByOrderIdAndStatus(Long orderId, OrderStatus status){
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(0,10,sort);
        return orderHistoryRepository.findByOrderIdAndNewStatus(orderId, status, pageable).getContent();
    }
}