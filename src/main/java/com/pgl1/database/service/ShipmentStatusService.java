package com.pgl1.database.service;

import com.pgl1.database.model.entity.ShipmentStatus;
import com.pgl1.database.repository.ShipmentStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipmentStatusService {
    private final ShipmentStatusRepository shipmentStatusRepository;

    public ShipmentStatusService(ShipmentStatusRepository shipmentStatusRepository){
        this.shipmentStatusRepository = shipmentStatusRepository;
    }

    public ShipmentStatus createShipmentStatus(ShipmentStatus shipmentStatus){
        return shipmentStatusRepository.save(shipmentStatus);
    }

    public ShipmentStatus updateShipmentStatus(ShipmentStatus shipmentStatus){
        return shipmentStatusRepository.save(shipmentStatus);
    }

    public void deleteShipmentStatus(Integer shipmentStatusId){
        shipmentStatusRepository.deleteById(shipmentStatusId);
    }
}
