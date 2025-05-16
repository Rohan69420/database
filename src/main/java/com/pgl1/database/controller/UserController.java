package com.pgl1.database.controller;

import com.pgl1.database.dto.request.user.UserUpdateDTO;
import com.pgl1.database.dto.request.user.UserWriteDTO;
import com.pgl1.database.dto.response.user.UserReadDTO;
import com.pgl1.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserReadDTO> createUser(@RequestBody UserWriteDTO userWriteDTO) {
        UserReadDTO savedUser = userService.createUser(userWriteDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UserReadDTO> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        UserReadDTO updatedUser = userService.updateUser(userUpdateDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
