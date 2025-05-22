package com.pgl1.database.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.pgl1.database.dto.request.OrderCreateDTO;
import com.pgl1.database.dto.request.OrderUpdateDTO;
import com.pgl1.database.dto.response.OrderViewDTO;
import com.pgl1.database.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){ this.orderService = orderService; }

    @PostMapping("/create")
    public ResponseEntity<OrderViewDTO> createOrder(@RequestBody @Valid OrderCreateDTO orderCreateDTO){
        OrderViewDTO savedOrder = orderService.createOrder(orderCreateDTO);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<OrderViewDTO> updateOrder(@RequestBody @Valid OrderUpdateDTO orderUpdateDTO){
        OrderViewDTO updatedOrder = orderService.updateOrder(orderUpdateDTO);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

}
