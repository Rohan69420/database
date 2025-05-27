package com.pgl1.database.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ViewLocationResponse {
    private Long id;
    private String country;
    private String city;
    private String street;
}
