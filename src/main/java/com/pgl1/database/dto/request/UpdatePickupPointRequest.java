package com.pgl1.database.dto.request;

import com.pgl1.database.model.entity.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePickupPointRequest {
    @NotNull(message = "There must be an id associated with the pickup point")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 20, message = "Name must be in-between 2 and 20 characters")
    private String name;

    @NotBlank(message = "The contact cannot be blank")
    @Size(min = 9, max = 10, message = "The contact number should be 9 to 10 digits only")
    private String contact;

    @NotNull(message = "Please add a location to the pickup point")
    private Location location;

    private String description;
}
