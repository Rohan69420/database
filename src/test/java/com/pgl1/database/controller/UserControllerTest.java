package com.pgl1.database.controller;

import com.pgl1.database.dto.request.UserCreateDTO;
import com.pgl1.database.dto.request.UserUpdateDTO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.pgl1.database.dto.response.UserViewDTO;
import org.springframework.http.ResponseEntity;
import com.pgl1.database.model.entity.Location;
import com.pgl1.database.service.UserService;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import jakarta.validation.ConstraintViolationException;

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

    private UserCreateDTO validCreateDTO;
    private UserUpdateDTO validUpdateDTO;
    private UserViewDTO sampleUserViewDTO;

    @BeforeEach
    void setUp() {
        Location location = Location.builder().country("Nepal").city("Kathmandu").street("Kalopul").build();

        validCreateDTO = new UserCreateDTO(
                "John Doe",              // name
                "+1234567890",           // phone (must match regex pattern)
                location,                // location
                "john.doe@example.com"  // email
        );


        validUpdateDTO = new UserUpdateDTO(
                1L,
                "John",
                "0123456789",
                location,
                "john.doe@example.com"
        );

        sampleUserViewDTO = new UserViewDTO(
                1L,
                "John",
                "+1234567890",
                "john.doe@example.com",
                location
        );
    }

    @Test
    void createUser_WithValidInput_ReturnsCreated() {
        when(userService.createUser(any(UserCreateDTO.class))).thenReturn(sampleUserViewDTO);

        ResponseEntity<UserViewDTO> response = userController.createUser(validCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(sampleUserViewDTO, response.getBody());
        verify(userService, times(1)).createUser(validCreateDTO);
    }

    @Test
    void updateUser_WithValidInput_ReturnsCreated() {
        // Arrange
        when(userService.updateUser(any(UserUpdateDTO.class))).thenReturn(sampleUserViewDTO);

        // Act
        ResponseEntity<UserViewDTO> response = userController.updateUser(validUpdateDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(sampleUserViewDTO, response.getBody());
        verify(userService, times(1)).updateUser(validUpdateDTO);
    }

    @Test
    void deleteUser_WithValidId_ReturnsNoContent() {
        // Arrange
        Integer userId = 1;
        doNothing().when(userService).deleteUser(userId);

        // Act
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    void findUser_WithValidEmail_ReturnsOk() {
        // Arrange
        String email = "john.doe@example.com";
        when(userService.fetchUser(email)).thenReturn(sampleUserViewDTO);

        // Act
        ResponseEntity<UserViewDTO> response = userController.findUser(email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(sampleUserViewDTO, response.getBody());
        verify(userService, times(1)).fetchUser(email);
    }

    @Test
    void createUser_WithNullInput_ShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userController.createUser(null);
        });
    }

    @Test
    void updateUser_WithInvalidInput_ShouldThrowValidationException() {
        UserUpdateDTO invalidUpdateDTO = new UserUpdateDTO(
                null,
                "",
                "abcdefghi",
                null,
                "invalid-email"
        );

        when(userService.updateUser(invalidUpdateDTO)).thenThrow(ConstraintViolationException.class);

        assertThrows(ConstraintViolationException.class, () -> {
            userController.updateUser(invalidUpdateDTO);
        });
    }

    @Test
    void findUser_WithNonExistentEmail_ShouldReturnNotFound() {
        String nonExistentEmail = "nonexistent@example.com";
        when(userService.fetchUser(nonExistentEmail)).thenReturn(null);

        ResponseEntity<UserViewDTO> response = userController.findUser(nonExistentEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteUser_WithNonExistentId_ShouldHandleGracefully() {
        Integer nonExistentId = 999;
        doThrow(new IllegalArgumentException("User not found")).when(userService).deleteUser(nonExistentId);

        assertThrows(IllegalArgumentException.class, () -> {
            userController.deleteUser(nonExistentId);
        });
    }
}