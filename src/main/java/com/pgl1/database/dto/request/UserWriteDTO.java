package com.pgl1.database.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserWriteDTO {
    private final String userName;
    private final String userPhone;
    private final String userLocation;

}

