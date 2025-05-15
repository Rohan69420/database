package com.pgl1.database.repository;

import com.pgl1.database.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByItemId(Integer ItemId);
}
