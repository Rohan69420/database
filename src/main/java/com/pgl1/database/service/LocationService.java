package com.pgl1.database.service;

import com.pgl1.database.dto.request.CreateLocationRequest;
import com.pgl1.database.dto.request.UpdateLocationRequest;
import com.pgl1.database.dto.response.ViewLocationResponse;
import com.pgl1.database.repository.LocationRepository;
import com.pgl1.database.mapper.LocationMapper;
import com.pgl1.database.model.entity.Location;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper){
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    public ViewLocationResponse createLocation(CreateLocationRequest createLocationRequest){
        Location createdLocation = locationRepository.save(locationMapper.locationCreateDTOToLocation(createLocationRequest));
        return locationMapper.locationToLocationViewDTO(createdLocation);
    }

    public ViewLocationResponse updateLocation(UpdateLocationRequest updateLocationRequest){
        Location updatedLocation = locationRepository.save(locationMapper.locationUpdateDTOToLocation(updateLocationRequest));
        return locationMapper.locationToLocationViewDTO(updatedLocation);
    }

    public List<ViewLocationResponse> fetchAll(){
        List<Location> locationList = locationRepository.findAll();
        return locationMapper.locationListToViewLocationResponseList(locationList);
    }


    public void deleteLocation(Long id){
        locationRepository.deleteById(id);
    }
}
