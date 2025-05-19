package com.pgl1.database.service;

import com.pgl1.database.dto.request.order.OrderUpdateDTO;
import com.pgl1.database.dto.request.order.OrderWriteDTO;
import com.pgl1.database.dto.response.order.OrderReadDTO;
import com.pgl1.database.mapper.OrderMapper;
import com.pgl1.database.model.entity.Order;
import com.pgl1.database.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public OrderReadDTO createOrder(OrderWriteDTO orderWriteDTO){
        Order savedOrder =  orderRepository.save(orderMapper.orderWriteDTOToOrder(orderWriteDTO));
        log.info("An order has been created");
        orderHistoryService.recordOrderCreation(savedOrder);
       return orderMapper.orderToOrderReadDTO(savedOrder);
    }

    public OrderReadDTO updateOrder(OrderUpdateDTO orderUpdateDTO){
        Order existingOrder = orderRepository.findById(orderUpdateDTO.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        Order previousState = new Order();
        BeanUtils.copyProperties(existingOrder, previousState);
        Order updatedOrder = orderRepository.save(orderMapper.orderUpdateDTOToOrder(orderUpdateDTO));

        log.info("An order has been updated");
        orderHistoryService.recordOrderUpdate(previousState, updatedOrder);
        return orderMapper.orderToOrderReadDTO(updatedOrder);
    }
}
