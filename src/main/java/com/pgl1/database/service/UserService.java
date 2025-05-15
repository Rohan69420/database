package com.pgl1.database.service;

import com.pgl1.database.dto.request.UserUpdateDTO;
import com.pgl1.database.dto.request.UserWriteDTO;
import com.pgl1.database.dto.response.UserReadDTO;
import com.pgl1.database.model.entity.User;
import com.pgl1.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserReadDTO createUser(UserWriteDTO userWriteDTO) {
        User user = new User();
        user.setUserName(userWriteDTO.getUserName());
        user.setUserPhone(userWriteDTO.getUserPhone());
        user.setUserLocation(userWriteDTO.getUserLocation());

        User savedUser =  userRepository.save(user);

        return convertToUserReadDTO(savedUser);
    }

    public UserReadDTO updateUser(UserUpdateDTO userUpdateDTO) {
        User user = new User();
        user.setUserId(userUpdateDTO.getUserId());
        user.setUserName(userUpdateDTO.getUserName());
        user.setUserPhone(userUpdateDTO.getUserPhone());
        user.setUserLocation(userUpdateDTO.getUserLocation());

        User savedUser =  userRepository.save(user);

        return convertToUserReadDTO(savedUser);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    private UserReadDTO convertToUserReadDTO(User user){
        UserReadDTO userReadDTO = new UserReadDTO();
        userReadDTO.setUserId(user.getUserId());
        userReadDTO.setUserName(user.getUserName());
        userReadDTO.setUserPhone(user.getUserPhone());
        userReadDTO.setUserLocation(user.getUserLocation());
        return userReadDTO;
    }

}
