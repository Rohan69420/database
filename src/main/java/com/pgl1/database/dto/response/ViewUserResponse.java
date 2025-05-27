package com.pgl1.database.dto.response;

import com.pgl1.database.model.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ViewUserResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Location location;
}
