package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.user.UserUpdateDTO;
import com.pgl1.database.dto.request.user.UserWriteDTO;
import com.pgl1.database.dto.response.user.UserReadDTO;
import com.pgl1.database.model.entity.Order;
import com.pgl1.database.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target="userId", ignore = true)
    @Mapping(target = "orders", source = "orders", qualifiedByName = "emptyListIfNull")
    @Mapping(target="locationId", source = "location")
    User userWriteDTOToUser(UserWriteDTO userWriteDTO);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "userPhone", source = "userPhone")
    UserReadDTO userToUserReadDTO(User user);

    @Mapping(target="userId", source="userId")
    @Mapping(target = "orders", source = "orders", qualifiedByName = "emptyListIfNull")
    @Mapping(target = "locationId", source = "location")
    User userUpdateDTOToUser(UserUpdateDTO userUpdateDTO);

    @Named("emptyListIfNull")
    default List<Order> emptyListIfNull(List<Order> list) {
        return list == null ? Collections.emptyList() : list;
    }
}