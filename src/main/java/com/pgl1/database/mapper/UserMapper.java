package com.pgl1.database.mapper;

import com.pgl1.database.dto.request.UpdateUserRequest;
import com.pgl1.database.dto.request.CreateUserRequest;
import com.pgl1.database.dto.response.ViewUserResponse;
import com.pgl1.database.model.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    User userCreateDTOToUser(CreateUserRequest createUserRequest);

    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "updatedTimestamp", ignore = true)
    User userUpdateDTOtoUser(UpdateUserRequest updateUserRequest);

    List<ViewUserResponse> userListToDTOList(List<User> users);

    ViewUserResponse userToUserViewDTO(User user);
}