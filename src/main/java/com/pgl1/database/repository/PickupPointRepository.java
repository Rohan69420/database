package com.pgl1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgl1.database.model.entity.PickupPoint;

import java.util.List;

public interface PickupPointRepository extends JpaRepository<PickupPoint, Integer> {
    List<PickupPoint> findByPickupPointId(Long pickupPointId);
}
