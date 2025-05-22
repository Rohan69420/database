package com.pgl1.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgl1.database.service.OrderHistoryService;

@RestController
@RequestMapping("/order-history")
public class OrderHistoryController {
    private final OrderHistoryService orderHistoryService;

    public OrderHistoryController(OrderHistoryService orderHistoryService){
        this.orderHistoryService = orderHistoryService;
    }

//    @PostMapping("/create")
//    public ResponseEntity<OrderHistory> createOrderHistory(@RequestBody OrderHistory orderHistory){
//        OrderHistory createdOrderHistory = orderHistoryService.createOrderHistory(orderHistory);
//        return new ResponseEntity<>(createdOrderHistory, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/update")
//    public ResponseEntity<OrderHistory> updateOrderHistory(@RequestBody OrderHistory orderHistory){
//        OrderHistory updatedOrderHistory =  orderHistoryService.updateOrderHistory(orderHistory);
//        return new ResponseEntity<>(orderHistory, HttpStatus.OK);
//    }
}
