package com.pgl1.database.controller;

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
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    void createUser_ShouldThrowException_WhenInvalidRequest() {
        CreateUserRequest invalidRequest = UserTestDataBuilder.createUserRequestBuilder()
                .email("invalid-email")
                .build();

        when(userService.createUser(invalidRequest))
                .thenThrow(new ConstraintViolationException("Validation failed", null));

        assertThrows(ConstraintViolationException.class,
                () -> userController.createUser(invalidRequest, mockRequest));

        verify(userService).createUser(invalidRequest);
    }

    // Update User Tests
    @Test
    void updateUser_ShouldReturnUpdatedUser() {
        Long userId = 1L;
        when(userService.updateUser(any(UpdateUserRequest.class)))
                .thenReturn(viewUser);

        ResponseEntity<GenericAPIResponse<ViewUserResponse>> response =
                userController.updateUser(updateUser, userId, mockRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("An user of id 1 has been created.", response.getBody().getMessage());
        assertEquals("/users", response.getBody().getPath());
        assertEquals(viewUser, response.getBody().getData());

        verify(userService).updateUser(updateUser);
    }

    @Test
    void updateUser_ShouldThrowException_WhenUserNotFound() {
        Long userId = 99L;
        when(userService.updateUser(any(UpdateUserRequest.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        assertThrows(ResponseStatusException.class,
                () -> userController.updateUser(updateUser, userId, mockRequest));

        verify(userService).updateUser(updateUser);
    }

    @Test
    void updateUser_ShouldThrowException_WhenInvalidRequest() {
        Long userId = 1L;
        UpdateUserRequest invalidRequest = UserTestDataBuilder.updateUserRequestBuilder()
                .email("invalid-email")
                .build();

        when(userService.updateUser(invalidRequest))
                .thenThrow(new ConstraintViolationException("Validation failed", null));

        assertThrows(ConstraintViolationException.class,
                () -> userController.updateUser(invalidRequest, userId, mockRequest));

        verify(userService).updateUser(invalidRequest);
    }

    // Delete User Tests
    @Test
    void deleteUser_ShouldReturnNoContent() {
        Long userId = 1L;
        doNothing().when(userService).deleteUser(userId);

        ResponseEntity<GenericAPIResponse<Void>> response =
                userController.deleteUser(userId, mockRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("An user of id 1has been deleted.", response.getBody().getMessage());
        assertEquals("/users", response.getBody().getPath());
        assertNull(response.getBody().getData());

        verify(userService).deleteUser(userId);
    }

    @Test
    void deleteUser_ShouldThrowException_WhenUserNotFound() {
        Long userId = 99L;
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"))
                .when(userService).deleteUser(userId);

        assertThrows(ResponseStatusException.class,
                () -> userController.deleteUser(userId, mockRequest));

        verify(userService).deleteUser(userId);
    }

    // Find User Tests
    @Test
    void findUser_ShouldReturnUser_WhenUserExists() {
        String email = "test@example.com";
        when(userService.fetchUser(email)).thenReturn(viewUser);

        ResponseEntity<GenericAPIResponse<ViewUserResponse>> response =
                userController.findUser(email, mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("User details found.", response.getBody().getMessage());
        assertEquals("/users", response.getBody().getPath());
        assertEquals(viewUser, response.getBody().getData());

        verify(userService).fetchUser(email);
    }

    @Test
    void findUser_ShouldThrowException_WhenUserNotFound() {
        String email = "nonexistent@example.com";
        when(userService.fetchUser(email))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        assertThrows(ResponseStatusException.class,
                () -> userController.findUser(email, mockRequest));

        verify(userService).fetchUser(email);
    }

    // Fetch All Users Tests
    @Test
    void fetchAllUsers_ShouldReturnUsersList() {
        int page = 0;
        int pageSize = 10;
        List<ViewUserResponse> users = Collections.singletonList(viewUser);

        when(userService.fetchAllUsers(page, pageSize)).thenReturn(users);

        ResponseEntity<GenericAPIResponse<List<ViewUserResponse>>> response =
                userController.fetchAllUsers(page, pageSize, mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("All users fetched", response.getBody().getMessage());
        assertEquals("/users", response.getBody().getPath());
        assertEquals(users, response.getBody().getData());
        assertEquals(1, response.getBody().getData().size());

        verify(userService).fetchAllUsers(page, pageSize);
    }

    @Test
    void fetchAllUsers_ShouldReturnEmptyList_WhenNoUsersExist() {
        int page = 0;
        int pageSize = 10;

        when(userService.fetchAllUsers(page, pageSize)).thenReturn(Collections.emptyList());

        ResponseEntity<GenericAPIResponse<List<ViewUserResponse>>> response =
                userController.fetchAllUsers(page, pageSize, mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("All users fetched", response.getBody().getMessage());
        assertEquals("/users", response.getBody().getPath());
        assertTrue(response.getBody().getData().isEmpty());

        verify(userService).fetchAllUsers(page, pageSize);
    }

    @Test
    void fetchAllUsers_ShouldThrowException_WhenInvalidPagination() {
        int page = -1;
        int pageSize = 0;

        when(userService.fetchAllUsers(page, pageSize))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid pagination parameters"));

        assertThrows(ResponseStatusException.class,
                () -> userController.fetchAllUsers(page, pageSize, mockRequest));

        verify(userService).fetchAllUsers(page, pageSize);
    }
}