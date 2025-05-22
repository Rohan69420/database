package com.pgl1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgl1.database.model.entity.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findById(Long id);

    List<Location> findByCountryAndCityAndStreet(String country, String city, String street);
}
