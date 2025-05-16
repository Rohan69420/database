package com.pgl1.database.dto.request.user;

import com.pgl1.database.model.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserWriteDTO {
    private String userName;
    private String userPhone;
    private String userLocation;
    private List<Order> orders;
    //refactor it as  private List<OrderWriteDTO> orders; after dtos are made
}

