package com.pgl1.database.controller;

import com.pgl1.database.model.entity.DeliveryStatus;
import com.pgl1.database.service.DeliveryStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery-status")
public class DeliveryStatusController {
    private final DeliveryStatusService deliveryStatusService;

    public DeliveryStatusController(DeliveryStatusService deliveryStatusService){
        this.deliveryStatusService = deliveryStatusService;
    }

    @PostMapping("/create")
    public ResponseEntity<DeliveryStatus> createDeliveryStatus(@RequestBody DeliveryStatus deliveryStatus){
        DeliveryStatus createdDeliveryStatus = deliveryStatusService.createDeliveryStatus(deliveryStatus);
        return new ResponseEntity<>(createdDeliveryStatus, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<DeliveryStatus> updateDeliveryStatus(@RequestBody DeliveryStatus deliveryStatus){
        DeliveryStatus updatedDeliveryStatus = deliveryStatusService.updateDeliveryStatus(deliveryStatus);
        return new ResponseEntity<>(updatedDeliveryStatus, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{deletedId}")
    public ResponseEntity<Void> deleteDeliveryStatus(@PathVariable Integer deliveryStatusId){
        deliveryStatusService.deleteDeliveryStatus(deliveryStatusId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
