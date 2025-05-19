package com.pgl1.database.dto.request.user;

import com.pgl1.database.model.entity.Location;
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
public class UserUpdateDTO {
    private Integer userId;
    private String userName;
    private String userPhone;
    private List<Order> orders;
    private Location location;
}
