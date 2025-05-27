package com.pgl1.database.controller;

import com.pgl1.database.enums.OrderStatus;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.model.entity.Order;
import com.pgl1.database.model.entity.OrderHistory;
import com.pgl1.database.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgl1.database.service.OrderHistoryService;

import java.util.List;

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

    @GetMapping("/{orderId}/{page}")
    public ResponseEntity<GenericAPIResponse<List<OrderHistory>>> getOrderHistoryByOrderId(@PathVariable Long orderId, @PathVariable Integer page, HttpServletRequest request){
        List<OrderHistory> orderHistoryList = orderHistoryService.fetchOrderHistoryByOrderId(orderId, page);
        return new ResponseEntity<>(ResponseUtil.success(orderHistoryList, "Order history fetched for given orderId", request.getRequestURI()), HttpStatus.OK);
    }

    @GetMapping("/{orderId}/status/{status}")
    public ResponseEntity<GenericAPIResponse<List<OrderHistory>>> getOrderHistoryByOrderId(@PathVariable Long orderId, @PathVariable OrderStatus status, HttpServletRequest request){
        List<OrderHistory> orderHistoryList = orderHistoryService.fetchOrderHistoryByOrderIdAndStatus(orderId, status);
        return new ResponseEntity<>(ResponseUtil.success(orderHistoryList, "Order history fetched for given orderId and status", request.getRequestURI()), HttpStatus.OK);
    }
}
