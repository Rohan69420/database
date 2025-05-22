package com.pgl1.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.pgl1.database.dto.request.LocationCreateDTO;
import com.pgl1.database.dto.request.LocationUpdateDTO;
import com.pgl1.database.dto.response.LocationViewDTO;
import com.pgl1.database.service.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @PostMapping("/create")
    public ResponseEntity<LocationViewDTO> createLocation(@RequestBody LocationCreateDTO locationCreateDTO){
        LocationViewDTO createdLocation = locationService.createLocation(locationCreateDTO);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<LocationViewDTO> updateLocation(@RequestBody LocationUpdateDTO locationUpdateDTO){
        LocationViewDTO updatedLocation = locationService.updateLocation(locationUpdateDTO);
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer locationId){
        locationService.deleteLocation(locationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
