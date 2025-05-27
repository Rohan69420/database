package com.pgl1.database.service;

import com.pgl1.database.dto.request.CreateOrderRequest;
import com.pgl1.database.dto.request.UpdateOrderRequest;
import com.pgl1.database.dto.response.ViewOrderResponse;
import com.pgl1.database.repository.OrderRepository;
import com.pgl1.database.mapper.OrderMapper;
import com.pgl1.database.model.entity.Order;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderHistoryService orderHistoryService;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, OrderHistoryService orderHistoryService){
        this.orderRepository = orderRepository;
        this.orderHistoryService = orderHistoryService;
        this.orderMapper = orderMapper;
    }

    public ViewOrderResponse createOrder(CreateOrderRequest createOrderRequest){
        Order savedOrder =  orderRepository.save(orderMapper.orderCreateDTOToOrder(createOrderRequest));
        log.info("An order has been created");
        orderHistoryService.recordOrderCreation(savedOrder);
       return orderMapper.orderToOrderViewDTO(savedOrder);
    }

    public ViewOrderResponse updateOrder(UpdateOrderRequest updateOrderRequest){
        Order existingOrder = orderRepository.findById(updateOrderRequest.getId()).orElse(null);
        Order previousState = new Order();
        BeanUtils.copyProperties(existingOrder, previousState);
        Order updatedOrder = orderRepository.save(orderMapper.orderUpdateDTOToOrder(updateOrderRequest));

        log.info("An order has been updated");
        orderHistoryService.recordOrderUpdate(previousState, updatedOrder);
        return orderMapper.orderToOrderViewDTO(updatedOrder);
    }
}
