package com.pgl1.database.dto.response;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class UserReadDTO {
    private final Integer userId;
    private final String userName;
    private final String userPhone;
    private final String userLocation;
}
