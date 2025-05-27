package com.pgl1.database.controller;

import com.pgl1.database.dto.response.ViewLocationResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.pgl1.database.dto.request.CreateLocationRequest;
import com.pgl1.database.dto.request.UpdateLocationRequest;
import com.pgl1.database.service.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<GenericAPIResponse<ViewLocationResponse>> createLocation(@Valid @RequestBody CreateLocationRequest createLocationRequest, HttpServletRequest request){
        ViewLocationResponse createdLocation = locationService.createLocation(createLocationRequest);
        return new ResponseEntity<>(ResponseUtil.success(createdLocation,"Location created successfully", request.getRequestURI()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericAPIResponse<ViewLocationResponse>> updateLocation(@Valid @RequestBody UpdateLocationRequest updateLocationRequest, @PathVariable Integer id, HttpServletRequest request){
        ViewLocationResponse updatedLocation = locationService.updateLocation(updateLocationRequest);
        return new ResponseEntity<>(ResponseUtil.success(updatedLocation, "Location updated successfully", request.getRequestURI()), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericAPIResponse<Void>> deleteLocation(@PathVariable Long locationId, HttpServletRequest request){
        locationService.deleteLocation(locationId);
        return new ResponseEntity<>(ResponseUtil.success(null, "Location deleted successfully", request.getRequestURI()),HttpStatus.NO_CONTENT);
    }
}
