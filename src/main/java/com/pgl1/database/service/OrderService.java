package com.pgl1.database.service;

import com.pgl1.database.model.entity.Order;
import com.pgl1.database.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order){
        Order createdOrder = new Order();
        createdOrder.setCustomerId(order.getCustomerId());
        createdOrder.setOrderDate(order.getOrderDate());

        Order savedOrder = orderRepository.save(createdOrder);

        return savedOrder;
    }

    public Order updateOrder(Order order){
        Order updatedOrder = orderRepository.save(order);
        return updatedOrder;
    }
}
