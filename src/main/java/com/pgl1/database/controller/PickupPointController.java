package com.pgl1.database.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pgl1.database.dto.request.PickupPointCreateDTO;
import com.pgl1.database.dto.request.PickupPointUpdateDTO;
import com.pgl1.database.dto.response.PickupPointViewDTO;
import com.pgl1.database.service.PickupPointService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/pickup-points")
public class PickupPointController {
    private final PickupPointService pickupPointService;

    public PickupPointController(PickupPointService pickupPointService){
        this.pickupPointService = pickupPointService;
    }

    @PostMapping("/create")
    public ResponseEntity<PickupPointViewDTO> createPickupPoint(@RequestBody @Valid PickupPointCreateDTO pickupPointCreateDTO){
        PickupPointViewDTO createdPickupPoint = pickupPointService.createPickupPoint(pickupPointCreateDTO);
        return new ResponseEntity<>(createdPickupPoint, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<PickupPointViewDTO> updatePickupPoint(@RequestBody @Valid PickupPointUpdateDTO pickupPointUpdateDTO){
        PickupPointViewDTO updatedPickupPoint = pickupPointService.updatePickupPoint(pickupPointUpdateDTO);
        return new ResponseEntity<>(updatedPickupPoint, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{pickupPointId}")
    public ResponseEntity<Void> deletePickupPoint(@PathVariable Long pickupPointId){
        pickupPointService.deletePickupPoint(pickupPointId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
