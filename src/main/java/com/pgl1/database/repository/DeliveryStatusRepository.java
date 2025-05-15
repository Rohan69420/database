package com.pgl1.database.repository;

import com.pgl1.database.model.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Integer> {
    List<DeliveryStatus> findByStatusId(Integer StatusId);
}
