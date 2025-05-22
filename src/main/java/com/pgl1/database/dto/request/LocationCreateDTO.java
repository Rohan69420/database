package com.pgl1.database.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationCreateDTO {
    private String country;
    private String city;
    private String street;
}
