package com.pgl1.database.controller;

import com.pgl1.database.model.entity.Location;
import com.pgl1.database.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @PostMapping("/create")
    public ResponseEntity<Location> createLocation(@RequestBody Location location){
        Location savedLocation = locationService.createLocation(location);
        return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Location> updateLocation(@RequestBody Location location){
        Location updatedLocation = locationService.updateLocation(location);
        return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer locationId){
        locationService.deleteLocation(locationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
