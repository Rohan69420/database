package com.pgl1.database.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationViewDTO {
    private Long id;
    private String country;
    private String city;
    private String street;
}
