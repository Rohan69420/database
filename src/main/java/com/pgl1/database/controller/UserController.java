package com.pgl1.database.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.pgl1.database.dto.request.UserUpdateDTO;
import com.pgl1.database.dto.request.UserCreateDTO;
import com.pgl1.database.dto.response.UserViewDTO;
import com.pgl1.database.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserViewDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserViewDTO savedUser = userService.createUser(userCreateDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UserViewDTO> updateUser(@Valid @RequestBody UserUpdateDTO updateUser) {
        UserViewDTO updatedUser = userService.updateUser(updateUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<UserViewDTO> findUser(@PathVariable String email){
        UserViewDTO user = userService.fetchUser(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
