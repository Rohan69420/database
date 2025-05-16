package com.pgl1.database.controller;

import com.pgl1.database.model.entity.PickupPoint;
import com.pgl1.database.service.PickupPointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pickup-points")
public class PickupPointController {
    private final PickupPointService pickupPointService;

    public PickupPointController(PickupPointService pickupPointService){
        this.pickupPointService = pickupPointService;
    }

    @PostMapping("/create")
    public ResponseEntity<PickupPoint> createPickupPoint(@RequestBody PickupPoint pickupPoint){
        PickupPoint createdPickupPoint = pickupPointService.createPickupPoint(pickupPoint);
        return new ResponseEntity<>(createdPickupPoint, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<PickupPoint> updatePickupPoint(@RequestBody PickupPoint pickupPoint){
        PickupPoint updatedPickupPoint = pickupPointService.updatePickupPoint(pickupPoint);
        return new ResponseEntity<>(updatedPickupPoint, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{pickupPointId}")
    public ResponseEntity<Void> deletePickupPoint(@PathVariable Integer pickupPointId){
        pickupPointService.deletePickupPoint(pickupPointId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
