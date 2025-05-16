package com.pgl1.database.service;

import com.pgl1.database.dto.request.user.UserUpdateDTO;
import com.pgl1.database.dto.request.user.UserWriteDTO;
import com.pgl1.database.dto.response.user.UserReadDTO;
import com.pgl1.database.mapper.UserMapper;
import com.pgl1.database.model.entity.User;
import com.pgl1.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserReadDTO createUser(UserWriteDTO userWriteDTO) {
        User user = userMapper.userWriteDTOToUser(userWriteDTO);

        User savedUser =  userRepository.save(user);

        return userMapper.userToUserReadDTO(savedUser);
    }

    public UserReadDTO updateUser(UserUpdateDTO userUpdateDTO) {
        User user = new User();
        user.setUserId(userUpdateDTO.getUserId());
        user.setUserName(userUpdateDTO.getUserName());
        user.setUserPhone(userUpdateDTO.getUserPhone());
        user.setUserLocation(userUpdateDTO.getUserLocation());

        User savedUser =  userRepository.save(user);

        return userMapper.userToUserReadDTO(savedUser);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }


}
