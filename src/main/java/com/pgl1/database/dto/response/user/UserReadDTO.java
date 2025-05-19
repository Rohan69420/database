package com.pgl1.database.dto.response.user;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserReadDTO {
    private Integer userId;
    private String userName;
    private String userPhone;
}
