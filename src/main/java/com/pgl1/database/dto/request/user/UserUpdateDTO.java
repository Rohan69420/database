package com.pgl1.database.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {
    private Integer userId;
    private String userName;
    private String userPhone;
    private String userLocation;
}
