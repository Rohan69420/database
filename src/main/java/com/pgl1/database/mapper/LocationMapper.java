package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.LocationCreateDTO;
import com.pgl1.database.dto.request.LocationUpdateDTO;
import com.pgl1.database.dto.response.LocationViewDTO;
import com.pgl1.database.model.entity.Location;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    @Mapping(target="id", ignore = true)
    Location locationCreateDTOToLocation(LocationCreateDTO locationCreateDTO);

    Location locationUpdateDTOToLocation(LocationUpdateDTO locationUpdateDTO);

    LocationViewDTO locationToLocationViewDTO(Location location);
}
