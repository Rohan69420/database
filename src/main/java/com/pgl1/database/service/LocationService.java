package com.pgl1.database.service;

import com.pgl1.database.model.entity.Location;
import com.pgl1.database.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location){
        return locationRepository.save(location);
    }

    public Location updateLocation(Location location){
        return locationRepository.save(location);
    }

    public void deleteLocation(Integer locationId){
        locationRepository.deleteById(locationId);
    }
}
