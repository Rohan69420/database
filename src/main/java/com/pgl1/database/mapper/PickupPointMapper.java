package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.PickupPointCreateDTO;
import com.pgl1.database.dto.request.PickupPointUpdateDTO;
import com.pgl1.database.dto.response.PickupPointViewDTO;
import com.pgl1.database.model.entity.PickupPoint;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PickupPointMapper {
    @Mapping(target = "id", ignore = true)
    PickupPoint pickupPointCreateDTOToPickupPoint(PickupPointCreateDTO pickupPointCreateDTO);

    PickupPoint pickupPointUpdateDTOToPickupPoint(PickupPointUpdateDTO pickupPointUpdateDTO);

    PickupPointViewDTO pickupPointToPickupPointViewDTO(PickupPoint pickupPoint);
}
