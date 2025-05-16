package com.pgl1.database.service;

import com.pgl1.database.model.entity.PickupPoint;
import com.pgl1.database.repository.PickupPointRepository;
import org.springframework.stereotype.Service;

@Service
public class PickupPointService {
    private final PickupPointRepository pickupPointRepository;

    public PickupPointService(PickupPointRepository pickupPointRepository){
        this.pickupPointRepository = pickupPointRepository;
    }

    public PickupPoint createPickupPoint(PickupPoint pickupPoint){
        return pickupPointRepository.save(pickupPoint);
    }

    public PickupPoint updatePickupPoint(PickupPoint pickupPoint){
        return pickupPointRepository.save(pickupPoint);
    }

    public void deletePickupPoint(Integer pickupPointId){
        pickupPointRepository.deleteById(pickupPointId);
    }
}
