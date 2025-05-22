package com.pgl1.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pgl1.database.model.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findById(Long userId);

    User findByEmail(String email);
}
