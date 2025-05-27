package com.pgl1.database.mockData;

import com.pgl1.database.dto.request.CreateUserRequest;
import com.pgl1.database.dto.request.UpdateUserRequest;
import com.pgl1.database.dto.response.ViewUserResponse;
import com.pgl1.database.model.entity.User;

public class UserTestDataBuilder {
    public static CreateUserRequest.CreateUserRequestBuilder createUserRequestBuilder() {
        return CreateUserRequest.builder().name("Test Joe").phone("9876543210").email("test@test.com");
    }

    public static UpdateUserRequest.UpdateUserRequestBuilder updateUserRequestBuilder() {
        return UpdateUserRequest.builder().id(1L).name("Test Joe").phone("9876543210").email("test@test.com");
    }

    public static ViewUserResponse.ViewUserResponseBuilder viewUserResponseBuilder() {
        return ViewUserResponse.builder().id(1L).name("Test Joe").phone("9876543210").email("test@test.com");
    }

    public static User.UserBuilder userBuilder(){
        return User.builder().id(1L).name("Test Joe").phone("9876543210").email("test@test.com");
    }
}
