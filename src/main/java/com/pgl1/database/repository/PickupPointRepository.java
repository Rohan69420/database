package com.pgl1.database.repository;

import com.pgl1.database.model.entity.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PickupPointRepository extends JpaRepository<PickupPoint, Integer> {
    List<PickupPoint> findByPickupPointId(Integer pickupPointId);
}
