package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.CreateOrderRequest;
import com.pgl1.database.dto.request.UpdateOrderRequest;
import com.pgl1.database.dto.response.ViewOrderResponse;
import com.pgl1.database.model.entity.Order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    Order orderCreateDTOToOrder(CreateOrderRequest createOrderRequest);

    @Mapping(target = "createdTimestamp", ignore = true)
    Order orderUpdateDTOToOrder(UpdateOrderRequest updateOrderRequest);

    ViewOrderResponse orderToOrderViewDTO(Order order);

}


