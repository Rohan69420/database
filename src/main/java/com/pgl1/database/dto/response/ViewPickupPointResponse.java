package com.pgl1.database.dto.response;

import com.pgl1.database.model.entity.Location;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewPickupPointResponse {
    private Long id;
    private String name;
    private String contact;
    private Location location;
    private String description;
}
