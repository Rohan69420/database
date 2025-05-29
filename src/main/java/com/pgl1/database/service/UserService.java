package com.pgl1.database.service;

import com.pgl1.database.dto.request.CreateUserRequest;
import com.pgl1.database.dto.request.UpdateUserRequest;
import com.pgl1.database.dto.response.ViewUserResponse;
import com.pgl1.database.mapper.UserMapper;
import com.pgl1.database.model.entity.User;
import com.pgl1.database.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ViewUserResponse createUser(CreateUserRequest createUserRequest) {
        createUserRequest.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        createUserRequest.setRoles(Arrays.asList("USER"));
        User savedUser =  userRepository.save(userMapper.userCreateDTOToUser(createUserRequest));
        log.info("A new user has been created");
        return userMapper.userToUserViewDTO(savedUser);
    }

    // Considering the Id will be fetched from the active session
    public ViewUserResponse updateUser(UpdateUserRequest updateUser) {
        User updatedUser = userRepository.save(userMapper.userUpdateDTOtoUser(updateUser));
        log.info("A user has been updated");
        return userMapper.userToUserViewDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("A user has been deleted");
    }

    public ViewUserResponse fetchUser(String email){
        User fetchedUser = userRepository.findByEmail(email);
        return userMapper.userToUserViewDTO(fetchedUser);
    }

    public List<ViewUserResponse> fetchAllUsers(Integer page, Integer pageSize){
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTimestamp");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        List<User> users = userRepository.findAll(pageable).getContent();
        return userMapper.userListToDTOList(users);
    }


}
