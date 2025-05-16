package com.pgl1.database.controller;

import com.pgl1.database.model.entity.Order;
import com.pgl1.database.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){ this.orderService = orderService; }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order savedOrder = orderService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        Order updatedOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

}
