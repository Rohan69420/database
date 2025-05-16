package com.pgl1.database.service;

import com.pgl1.database.model.entity.OrderHistory;
import com.pgl1.database.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderHistoryService {

    @Autowired
    private final OrderHistoryRepository orderHistoryRepository;

    public OrderHistoryService(OrderHistoryRepository orderHistoryRepository){
        this.orderHistoryRepository = orderHistoryRepository;
    }

    public OrderHistory createOrderHistory(OrderHistory orderHistory){
        OrderHistory savedOrderHistory = orderHistoryRepository.save(orderHistory);
        return savedOrderHistory;
    }

    public OrderHistory updateOrderHistory(OrderHistory orderHistory){
        OrderHistory updatedOrderHistory = orderHistoryRepository.save(orderHistory);
        return updatedOrderHistory;
    }
}
