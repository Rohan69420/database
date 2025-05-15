package com.pgl1.database.repository;

import com.pgl1.database.model.entity.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentStatusRepository extends JpaRepository<ShipmentStatus, Integer> {
    List<ShipmentStatus> findByShipmentStatusId(Integer shipmentStatusId);
}
