package com.pgl1.database.controller;


import com.pgl1.database.dto.request.CreateUserRequest;
import com.pgl1.database.dto.request.RefreshTokenRequest;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.model.entity.RefreshToken;
import com.pgl1.database.model.entity.User;
import com.pgl1.database.service.RefreshTokenService;
import com.pgl1.database.service.UserDetailServiceImpl;
import com.pgl1.database.service.UserService;
import com.pgl1.database.util.JwtUtil;
import com.pgl1.database.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RefreshTokenService refreshTokenService;

//    @PostMapping("/sign-up")
//    public void signup(@RequestBody CreateUserRequest createUserRequest){
//        userService.createUser(createUserRequest);
//    }

    @PostMapping("/login")
    public ResponseEntity<GenericAPIResponse<List<String>>> login(@RequestBody CreateUserRequest createUserRequest, HttpServletRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(createUserRequest.getUsername(), createUserRequest.getPassword()));
        UserDetails userDetails = userDetailService.loadUserByUsername(createUserRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());
        String refreshTokenString = refreshToken.getRefreshToken();
        return new ResponseEntity<>(ResponseUtil.success(Arrays.asList(jwt, refreshTokenString), "User authenticated successfully", request.getRequestURI()), HttpStatus.CREATED);

    }

    @PostMapping("/refresh-jwt")
    public ResponseEntity<GenericAPIResponse<String>> refreshJwtToken(@RequestBody RefreshTokenRequest refreshTokenRequest, HttpServletRequest request) throws Exception{
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
        User user = refreshToken.getUser();
        String token = jwtUtil.generateToken(user.getUsername());
        return new ResponseEntity<>(ResponseUtil.success(token, "JWT token refreshed successfully", request.getRequestURI()), HttpStatus.CREATED);


    }
}
