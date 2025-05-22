package com.pgl1.database.service;

import com.pgl1.database.dto.request.PickupPointCreateDTO;
import com.pgl1.database.dto.request.PickupPointUpdateDTO;
import com.pgl1.database.repository.PickupPointRepository;
import com.pgl1.database.dto.response.PickupPointViewDTO;
import com.pgl1.database.mapper.PickupPointMapper;
import com.pgl1.database.model.entity.PickupPoint;

import org.springframework.stereotype.Service;

@Service
public class PickupPointService {
    private final PickupPointRepository pickupPointRepository;
    private final PickupPointMapper pickupPointMapper;

    public PickupPointService(PickupPointRepository pickupPointRepository, PickupPointMapper pickupPointMapper){
        this.pickupPointRepository = pickupPointRepository;
        this.pickupPointMapper = pickupPointMapper;
    }

    public PickupPointViewDTO createPickupPoint(PickupPointCreateDTO pickupPointCreateDTO){
        PickupPoint createdPickupPoint = pickupPointRepository.save(pickupPointMapper.pickupPointCreateDTOToPickupPoint(pickupPointCreateDTO));
        return pickupPointMapper.pickupPointToPickupPointViewDTO(createdPickupPoint);
    }

    public PickupPointViewDTO updatePickupPoint(PickupPointUpdateDTO pickupPointUpdateDTO){
        PickupPoint updatedPickupPoint = pickupPointRepository.save(pickupPointMapper.pickupPointUpdateDTOToPickupPoint(pickupPointUpdateDTO));
        return pickupPointMapper.pickupPointToPickupPointViewDTO(updatedPickupPoint);
    }

    public void deletePickupPoint(Long id){
        pickupPointRepository.deleteById(id);
    }
}
