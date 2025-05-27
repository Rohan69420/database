package com.pgl1.database.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class UpdateLocationRequest {
    @NotNull(message = "The location id cannot be null")
    private Long id;

    @NotBlank(message = "The country name cannot be blank")
    @Size(min = 4, max = 20, message = "The country name must be between 4 and 20 characters")
    private String country;

    @NotBlank(message = "The city name cannot be empty")
    @Size(min = 2, max = 20, message = "The city name must be between 2 and 20 characters")
    private String city;

    @NotBlank(message = "The street name cannot be empty")
    @Size(min = 2, max = 20, message = "The street name must be in-between 2 and 20 characters")
    private String street;
}
