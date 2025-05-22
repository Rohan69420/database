package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.UserUpdateDTO;
import com.pgl1.database.dto.request.UserCreateDTO;
import com.pgl1.database.dto.response.UserViewDTO;
import com.pgl1.database.model.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    User userCreateDTOToUser(UserCreateDTO userCreateDTO);

    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    User userUpdateDTOtoUser(UserUpdateDTO userUpdateDTO);


    UserViewDTO userToUserViewDTO(User user);
}