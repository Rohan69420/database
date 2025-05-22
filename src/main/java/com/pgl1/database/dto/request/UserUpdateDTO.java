package com.pgl1.database.dto.request;

import com.pgl1.database.model.entity.Location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDTO {
    @NotNull(message = "User id cannot be null")
    private Long id;

    @NotBlank(message="Name cannot be empty")
    private String name;

    @NotBlank(message="Phone number cannot be empty")
    @Pattern(regexp = "^\\+?[0-9\\s\\-()]{7,20}$", message = "Invalid phone number format")
    @Size(min = 9, max = 10, message = "The length should be between 9 to 10")
    private String phone;

    private Location location;

    @NotBlank(message = "Email cannot be left blank")
    @Email(message = "This is not a valid email")
    private String email;
}

