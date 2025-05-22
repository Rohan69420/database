package com.pgl1.database.dto.response;

import com.pgl1.database.model.entity.PickupPoint;
import com.pgl1.database.enums.OrderStatus;
import com.pgl1.database.model.entity.Item;
import com.pgl1.database.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderViewDTO {
    private Integer id;
    private String name;
    private List<Item> items;
    private User user;
    private PickupPoint source;
    private PickupPoint destination;
    private String type;
    private OrderStatus status;
}
