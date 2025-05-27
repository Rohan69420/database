package com.pgl1.database.dto.request;

import com.pgl1.database.model.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateItemRequest {
    @NotNull(message = "User cannot be null")
    private User user;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    private String description;

    @NotNull(message = "Please add item weight")
    private Float weight;
}
