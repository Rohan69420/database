package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.CreatePickupPointRequest;
import com.pgl1.database.dto.request.UpdatePickupPointRequest;
import com.pgl1.database.dto.response.ViewPickupPointResponse;
import com.pgl1.database.model.entity.PickupPoint;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PickupPointMapper {
    @Mapping(target = "id", ignore = true)
    PickupPoint pickupPointCreateDTOToPickupPoint(CreatePickupPointRequest createPickupPointRequest);

    PickupPoint pickupPointUpdateDTOToPickupPoint(UpdatePickupPointRequest updatePickupPointRequest);

    List<ViewPickupPointResponse> pickupPointListToViewPickupPointResponseList(List<PickupPoint> pickupPoints);

    ViewPickupPointResponse pickupPointToPickupPointViewDTO(PickupPoint pickupPoint);
}
