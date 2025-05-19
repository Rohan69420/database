package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.order.OrderUpdateDTO;
import com.pgl1.database.dto.request.order.OrderWriteDTO;
import com.pgl1.database.dto.response.order.OrderReadDTO;
import com.pgl1.database.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target="orderId", ignore = true)
    @Mapping(target="pickupPoint.pickupPointId", source = "pickupPointId")
    @Mapping(target = "user.userId", source = "userId")
    Order orderWriteDTOToOrder(OrderWriteDTO orderWriteDTO);

    @Mapping(target="pickupPoint.pickupPointId", source = "pickupPointId")
    @Mapping(target = "user.userId", source = "userId")
    Order orderUpdateDTOToOrder(OrderUpdateDTO orderUpdateDTO);

    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "pickupPointId", source = "pickupPoint.pickupPointId")
    OrderReadDTO orderToOrderReadDTO(Order order);

}


