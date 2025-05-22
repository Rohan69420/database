package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.OrderCreateDTO;
import com.pgl1.database.dto.request.OrderUpdateDTO;
import com.pgl1.database.dto.response.OrderViewDTO;
import com.pgl1.database.model.entity.Order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "createdTimestamp", ignore = true)
    Order orderCreateDTOToOrder(OrderCreateDTO orderCreateDTO);

    @Mapping(target = "createdTimestamp", ignore = true)
    Order orderUpdateDTOToOrder(OrderUpdateDTO orderUpdateDTO);

    OrderViewDTO orderToOrderViewDTO(Order order);

}


