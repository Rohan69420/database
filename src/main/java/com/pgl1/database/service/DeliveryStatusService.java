package com.pgl1.database.service;

import com.pgl1.database.model.entity.DeliveryStatus;
import com.pgl1.database.repository.DeliveryStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryStatusService {
    private final DeliveryStatusRepository deliveryStatusRepository;

    public DeliveryStatusService(DeliveryStatusRepository deliveryStatusRepository){
        this.deliveryStatusRepository = deliveryStatusRepository;
    }

    public DeliveryStatus createDeliveryStatus(DeliveryStatus deliveryStatus){
        return deliveryStatusRepository.save(deliveryStatus);
    }

    public DeliveryStatus updateDeliveryStatus(DeliveryStatus deliveryStatus){
        return deliveryStatusRepository.save(deliveryStatus);
    }

    public void deleteDeliveryStatus(Integer deliveryStatusId){
        deliveryStatusRepository.deleteById(deliveryStatusId);
    }
}
