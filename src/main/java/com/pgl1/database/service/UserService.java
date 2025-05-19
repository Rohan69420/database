package com.pgl1.database.service;

import com.pgl1.database.dto.request.user.UserUpdateDTO;
import com.pgl1.database.dto.request.user.UserWriteDTO;
import com.pgl1.database.dto.response.user.UserReadDTO;
import com.pgl1.database.mapper.UserMapper;
import com.pgl1.database.model.entity.User;
import com.pgl1.database.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserReadDTO createUser(UserWriteDTO userWriteDTO) {
        User savedUser =  userRepository.save(userMapper.userWriteDTOToUser(userWriteDTO));
        log.info("A new user has been created");
        return userMapper.userToUserReadDTO(savedUser);
    }

    public UserReadDTO updateUser(UserUpdateDTO userUpdateDTO) {
        User updatedUser = userRepository.save(userMapper.userUpdateDTOToUser(userUpdateDTO));
        log.info("A user has been updated");
        return userMapper.userToUserReadDTO(updatedUser);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
        log.info("A user has been deleted");
    }


}
