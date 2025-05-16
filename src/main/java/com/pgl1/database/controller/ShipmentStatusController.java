package com.pgl1.database.controller;

import com.pgl1.database.model.entity.ShipmentStatus;
import com.pgl1.database.service.ShipmentStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipment-status")
public class ShipmentStatusController {
    private final ShipmentStatusService shipmentStatusService;

    public ShipmentStatusController(ShipmentStatusService shipmentStatusService){
        this.shipmentStatusService = shipmentStatusService;
    }

    @PostMapping("/create")
    public ResponseEntity<ShipmentStatus> createShipmentStatus(@RequestBody ShipmentStatus shipmentStatus){
        ShipmentStatus createdShipmentStatus = shipmentStatusService.createShipmentStatus(shipmentStatus);
        return new ResponseEntity<>(createdShipmentStatus, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ShipmentStatus> updateShipmentStatus(@RequestBody ShipmentStatus shipmentStatus){
        ShipmentStatus updatedShipmentStatus = shipmentStatusService.updateShipmentStatus(shipmentStatus);
        return new ResponseEntity<>(updatedShipmentStatus, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{shipmentStatusId}")
    public ResponseEntity<Void> deleteShipmentId(@PathVariable Integer shipmentStatusId){
        shipmentStatusService.deleteShipmentStatus(shipmentStatusId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
