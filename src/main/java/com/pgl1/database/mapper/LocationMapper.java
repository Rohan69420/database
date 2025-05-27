package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.CreateLocationRequest;
import com.pgl1.database.dto.request.UpdateLocationRequest;
import com.pgl1.database.dto.response.ViewLocationResponse;
import com.pgl1.database.model.entity.Location;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    @Mapping(target="id", ignore = true)
    Location locationCreateDTOToLocation(CreateLocationRequest createLocationRequest);

    Location locationUpdateDTOToLocation(UpdateLocationRequest updateLocationRequest);

    ViewLocationResponse locationToLocationViewDTO(Location location);
}
