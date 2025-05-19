package com.pgl1.database.dto.request.user;

import com.pgl1.database.model.entity.Location;
import com.pgl1.database.model.entity.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserWriteDTO {
    private String userName;
    private String userPhone;
    private List<Order> orders;
    private Location location;
    //refactor it as  private List<OrderWriteDTO> orders; after dtos are made
}

