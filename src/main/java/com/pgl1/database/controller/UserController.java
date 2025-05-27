package com.pgl1.database.controller;

import com.pgl1.database.dto.request.UpdateUserRequest;
import com.pgl1.database.dto.response.ViewUserResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.pgl1.database.dto.request.CreateUserRequest;
import com.pgl1.database.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<GenericAPIResponse<ViewUserResponse>> createUser(@Valid @RequestBody CreateUserRequest createUserRequest, HttpServletRequest request) {
        ViewUserResponse savedUser = userService.createUser(createUserRequest);
        return new ResponseEntity<>(ResponseUtil.success(savedUser, "An user has been created", request.getRequestURI()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericAPIResponse<ViewUserResponse>> updateUser(@Valid @RequestBody UpdateUserRequest updateUser, @PathVariable Long id, HttpServletRequest request) {
        ViewUserResponse updatedUser = userService.updateUser(updateUser);
        return new ResponseEntity<>(ResponseUtil.success(updatedUser, "An user of id " + id.toString() + " has been created.", request.getRequestURI()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericAPIResponse<Void>> deleteUser(@PathVariable Long id, HttpServletRequest request){
        userService.deleteUser(id);
        return new ResponseEntity<>(ResponseUtil.success(null, "An user of id " + id.toString() + "has been deleted.", request.getRequestURI()), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<GenericAPIResponse<ViewUserResponse>> findUser(@PathVariable String email, HttpServletRequest request){
        ViewUserResponse user = userService.fetchUser(email);
        return new ResponseEntity<>(ResponseUtil.success(user, "User details found.", request.getRequestURI()), HttpStatus.OK);
    }
    @GetMapping("/fetch/{page}/{pageSize}")
    public ResponseEntity<GenericAPIResponse<List<ViewUserResponse>>> fetchAllUsers(@PathVariable Integer page, @PathVariable Integer pageSize,HttpServletRequest request){
        List<ViewUserResponse> allUsers = userService.fetchAllUsers(page, pageSize);
        return new ResponseEntity<>(ResponseUtil.success(allUsers, "All users fetched", request.getRequestURI()), HttpStatus.OK);
    }

}
