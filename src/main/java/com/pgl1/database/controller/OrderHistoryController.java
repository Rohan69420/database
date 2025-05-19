package com.pgl1.database.controller;

import com.pgl1.database.model.entity.OrderHistory;
import com.pgl1.database.service.OrderHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
