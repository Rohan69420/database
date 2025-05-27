package com.pgl1.database.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ViewItemResponse {
    private Long id;
    private String name;
    private String description;
    private Float weight;
    private LocalDateTime createdTimestamp;
}
