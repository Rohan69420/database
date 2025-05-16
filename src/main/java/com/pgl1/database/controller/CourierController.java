package com.pgl1.database.controller;

import com.pgl1.database.model.entity.Courier;
import com.pgl1.database.service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couriers")
public class CourierController {
    private final CourierService courierService;

    public CourierController(CourierService courierService){
        this.courierService = courierService;
    }

    @PostMapping("/create")
    public ResponseEntity<Courier> createCourier(@RequestBody Courier courier){
        Courier savedCourier = courierService.createCourier(courier);
        return new ResponseEntity<>(savedCourier, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Courier> updateCourier(@RequestBody Courier courier){
        Courier updatedCourier = courierService.updateCourier(courier);
        return new ResponseEntity<>(updatedCourier, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{courierId}")
    public ResponseEntity<Void> deleteCourier(@PathVariable Integer courierId){
        courierService.deleteCourier(courierId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
