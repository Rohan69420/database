package com.pgl1.database.service;

import com.pgl1.database.model.entity.Order;
import com.pgl1.database.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order){

       return orderRepository.save(order);
    }

    public Order updateOrder(Order order){
        Order updatedOrder = orderRepository.save(order);
        return updatedOrder;
    }
}
