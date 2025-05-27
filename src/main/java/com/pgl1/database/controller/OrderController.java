package com.pgl1.database.controller;

import com.pgl1.database.dto.response.ViewOrderResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.pgl1.database.dto.request.CreateOrderRequest;
import com.pgl1.database.dto.request.UpdateOrderRequest;
import com.pgl1.database.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){ this.orderService = orderService; }

    @PostMapping
    public ResponseEntity<GenericAPIResponse<ViewOrderResponse>> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest, HttpServletRequest request){
        ViewOrderResponse savedOrder = orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(ResponseUtil.success(savedOrder, "An order has been created.", request.getRequestURI()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericAPIResponse<ViewOrderResponse>> updateOrder(@Valid @RequestBody UpdateOrderRequest updateOrderRequest, @PathVariable Long id, HttpServletRequest request){
        ViewOrderResponse updatedOrder = orderService.updateOrder(updateOrderRequest);
        return new ResponseEntity<>(ResponseUtil.success(updatedOrder, "An order of id " + id.toString() + " has been updated", request.getRequestURI()), HttpStatus.OK);
    }

}
