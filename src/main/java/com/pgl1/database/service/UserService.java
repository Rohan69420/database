package com.pgl1.database.service;

import com.pgl1.database.dto.request.UserUpdateDTO;
import com.pgl1.database.dto.request.UserCreateDTO;
import com.pgl1.database.dto.response.UserViewDTO;
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

    public UserViewDTO createUser(UserCreateDTO userCreateDTO) {
        User savedUser =  userRepository.save(userMapper.userCreateDTOToUser(userCreateDTO));
        log.info("A new user has been created");
        return userMapper.userToUserViewDTO(savedUser);
    }

    // Considering the Id will be fetched from the active session
    public UserViewDTO updateUser(UserUpdateDTO updateUser) {
        User updatedUser = userRepository.save(userMapper.userUpdateDTOtoUser(updateUser));
        log.info("A user has been updated");
        return userMapper.userToUserViewDTO(updatedUser);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
        log.info("A user has been deleted");
    }

    public UserViewDTO fetchUser(String email){
        User fetchedUser = userRepository.findByEmail(email);
        return userMapper.userToUserViewDTO(fetchedUser);
    }


}
