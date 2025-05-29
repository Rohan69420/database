package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.CreateLocationRequest;
import com.pgl1.database.dto.request.UpdateLocationRequest;
import com.pgl1.database.dto.response.ViewLocationResponse;
import com.pgl1.database.model.entity.Location;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    @Mapping(target="id", ignore = true)
    Location locationCreateDTOToLocation(CreateLocationRequest createLocationRequest);

    Location locationUpdateDTOToLocation(UpdateLocationRequest updateLocationRequest);

    List<ViewLocationResponse> locationListToViewLocationResponseList(List<Location> locationList);

    ViewLocationResponse locationToLocationViewDTO(Location location);
}
