package com.pgl1.database.controller;

import com.pgl1.database.dto.request.CreatePickupPointRequest;
import com.pgl1.database.dto.request.UpdatePickupPointRequest;
import com.pgl1.database.dto.response.ViewPickupPointResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.model.entity.PickupPoint;
import com.pgl1.database.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pgl1.database.service.PickupPointService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/pickup-points")
public class PickupPointController {
    private final PickupPointService pickupPointService;

    public PickupPointController(PickupPointService pickupPointService){
        this.pickupPointService = pickupPointService;
    }

    @PostMapping
    public ResponseEntity<GenericAPIResponse<ViewPickupPointResponse>> createPickupPoint(@Valid @RequestBody CreatePickupPointRequest createPickupPointRequest, HttpServletRequest request){
        ViewPickupPointResponse createdPickupPoint = pickupPointService.createPickupPoint(createPickupPointRequest);
        return new ResponseEntity<>(ResponseUtil.success(createdPickupPoint, "A pickup point has been added", request.getRequestURI()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericAPIResponse<ViewPickupPointResponse>> updatePickupPoint(@Valid @RequestBody UpdatePickupPointRequest updatePickupPointRequest, @PathVariable Long id, HttpServletRequest request){
        ViewPickupPointResponse updatedPickupPoint = pickupPointService.updatePickupPoint(updatePickupPointRequest);
        return new ResponseEntity<>(ResponseUtil.success(updatedPickupPoint, "The pickup point of id " + id.toString() + " has been updated", request.getRequestURI()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericAPIResponse<Void>> deletePickupPoint(@PathVariable Long id, HttpServletRequest request){
        pickupPointService.deletePickupPoint(id);
        return new ResponseEntity<>(ResponseUtil.success(null, "A pickup point of id " + id.toString() + " has been deleted", request.getRequestURI()),HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<GenericAPIResponse<List<ViewPickupPointResponse>>> getAllPickupPoints(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ViewPickupPointResponse> pickupPointResponses = pickupPointService.fetchAll();
        return new ResponseEntity<>(ResponseUtil.success(pickupPointResponses, "All pickup points fetched.", request.getRequestURI()),HttpStatus.OK);
    }
}
