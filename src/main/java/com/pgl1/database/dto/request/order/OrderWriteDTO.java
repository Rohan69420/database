package com.pgl1.database.dto.request.order;

import com.pgl1.database.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderWriteDTO {
    private String orderDate;
    private Integer userId;
    private String orderStatus;
    private Integer pickupPointId;
}