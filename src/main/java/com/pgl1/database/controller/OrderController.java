package com.pgl1.database.controller;

import com.pgl1.database.dto.request.order.OrderUpdateDTO;
import com.pgl1.database.dto.request.order.OrderWriteDTO;
import com.pgl1.database.dto.response.order.OrderReadDTO;
import com.pgl1.database.service.OrderService;
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

    public OrderController(OrderService orderService){ this.orderService = orderService; }

    @PostMapping("/create")
    public ResponseEntity<OrderReadDTO> createOrder(@RequestBody OrderWriteDTO orderWriteDTO){
        OrderReadDTO savedOrder = orderService.createOrder(orderWriteDTO);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<OrderReadDTO> updateOrder(@RequestBody OrderUpdateDTO orderUpdateDTO){
        OrderReadDTO updatedOrder = orderService.updateOrder(orderUpdateDTO);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

}
