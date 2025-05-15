package com.pgl1.database.repository;

import com.pgl1.database.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserId(Integer userId);
}
