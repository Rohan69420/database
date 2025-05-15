package com.pgl1.database.repository;

import com.pgl1.database.model.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository  extends JpaRepository<Courier, Integer > {
    List<Courier> findByCourierId(Integer courierId);
}
