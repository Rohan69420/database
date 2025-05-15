package com.pgl1.database.repository;

import com.pgl1.database.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByLocationId(Integer locationId);
}
