package com.pgl1.database.service;

import com.pgl1.database.model.entity.Order;
import com.pgl1.database.model.entity.OrderHistory;
import com.pgl1.database.repository.OrderHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;

    @Transactional
    public void recordOrderCreation(Order order) {
        OrderHistory history = OrderHistory.builder()
                .order(order)
                .changeType(OrderHistory.ChangeType.CREATED)
                .previousStatus(null)
                .newStatus(order.getOrderStatus())
                .build();

        orderHistoryRepository.save(history);
        log.info("Recorded order creation for order ID: {}", order.getOrderId());
    }

    @Transactional
    public void recordOrderUpdate(Order previousState, Order newState) {
        if (!previousState.getOrderStatus().equals(newState.getOrderStatus())) {
            OrderHistory history = OrderHistory.builder()
                    .order(newState)
                    .changeType(OrderHistory.ChangeType.STATUS_CHANGED)
                    .previousStatus(previousState.getOrderStatus())
                    .newStatus(newState.getOrderStatus())
                    .build();

            orderHistoryRepository.save(history);
            log.info("Recorded status change for order ID: {}", newState.getOrderId());
        }
        else {

            // Record general updates (non-status changes)
            OrderHistory updateHistory = OrderHistory.builder()
                    .order(newState)
                    .changeType(OrderHistory.ChangeType.UPDATED)
                    .previousStatus(previousState.getOrderStatus())
                    .newStatus(newState.getOrderStatus())
                    .build();

            orderHistoryRepository.save(updateHistory);
            log.info("Recorded order update for order ID: {}", newState.getOrderId());
        }

    }
}