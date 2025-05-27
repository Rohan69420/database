package com.pgl1.database.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateLocationRequest {
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
}
