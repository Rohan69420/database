package com.pgl1.database.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderReadDTO {
    private Integer orderId;
    private String orderDate;
    private Integer userId;
    private String orderStatus;
    private Integer pickupPointId;
}
