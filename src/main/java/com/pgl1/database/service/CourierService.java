package com.pgl1.database.service;

import com.pgl1.database.model.entity.Courier;
import com.pgl1.database.repository.CourierRepository;
import org.springframework.stereotype.Service;

@Service
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public Courier createCourier(Courier courier){
        return courierRepository.save(courier);
    }

    public Courier updateCourier(Courier courier){
        return courierRepository.save(courier);
    }

    public void deleteCourier(Integer courierId){
        courierRepository.deleteById(courierId);
    }
}
