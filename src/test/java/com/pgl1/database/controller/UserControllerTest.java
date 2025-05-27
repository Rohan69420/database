package com.pgl1.database.controller;

import com.pgl1.database.dto.request.CreateItemRequest;
import com.pgl1.database.dto.request.CreateUserRequest;
import com.pgl1.database.dto.request.UpdateUserRequest;
import com.pgl1.database.dto.response.ViewUserResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.mockData.LocationTestDataBuilder;
import com.pgl1.database.mockData.UserTestDataBuilder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.pgl1.database.model.entity.Location;
import com.pgl1.database.service.UserService;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import jakarta.validation.ConstraintViolationException;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private CreateUserRequest createUser;
    private UpdateUserRequest updateUser;
    private ViewUserResponse viewUser;

    MockHttpServletRequest mockRequest = new MockHttpServletRequest();

    @BeforeEach
    void setUp() {
        Location location = LocationTestDataBuilder.locationBuilder().build();

        createUser = UserTestDataBuilder.createUserRequestBuilder().build();
        updateUser = UserTestDataBuilder.updateUserRequestBuilder().build();
        viewUser = UserTestDataBuilder.viewUserResponseBuilder().build();

        mockRequest.setRequestURI("/users");
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        when(userService.createUser(any(CreateUserRequest.class)))
                .thenReturn(viewUser);

        ResponseEntity<GenericAPIResponse<ViewUserResponse>> response =
                userController.createUser(createUser, mockRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("An user has been created", response.getBody().getMessage());
        assertEquals("/users", response.getBody().getPath());
        assertEquals(viewUser, response.getBody().getData());  // More specific than full object comparison

        verify(userService).createUser(createUser);
    }
}