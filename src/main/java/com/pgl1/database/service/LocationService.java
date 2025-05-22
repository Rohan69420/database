package com.pgl1.database.service;

import com.pgl1.database.dto.request.LocationCreateDTO;
import com.pgl1.database.dto.request.LocationUpdateDTO;
import com.pgl1.database.dto.response.LocationViewDTO;
import com.pgl1.database.repository.LocationRepository;
import com.pgl1.database.mapper.LocationMapper;
import com.pgl1.database.model.entity.Location;

import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper){
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    public LocationViewDTO createLocation(LocationCreateDTO locationCreateDTO){
        Location createdLocation = locationRepository.save(locationMapper.locationCreateDTOToLocation(locationCreateDTO));
        return locationMapper.locationToLocationViewDTO(createdLocation);
    }

    public LocationViewDTO updateLocation(LocationUpdateDTO locationUpdateDTO){
        Location updatedLocation = locationRepository.save(locationMapper.locationUpdateDTOToLocation(locationUpdateDTO));
        return locationMapper.locationToLocationViewDTO(updatedLocation);
    }


    public void deleteLocation(Integer locationId){
        locationRepository.deleteById(locationId);
    }
}
