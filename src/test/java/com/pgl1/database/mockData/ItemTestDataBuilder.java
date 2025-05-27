package com.pgl1.database.mockData;

import com.pgl1.database.dto.request.CreateItemRequest;
import com.pgl1.database.dto.request.UpdateItemRequest;
import com.pgl1.database.dto.response.ViewItemResponse;
import com.pgl1.database.model.entity.User;

public class ItemTestDataBuilder {

    public static CreateItemRequest.CreateItemRequestBuilder createItemRequestBuilder(){
        return CreateItemRequest.builder().name("Test item").description("This is the item description").weight(10.2f);
    }

    public static UpdateItemRequest.UpdateItemRequestBuilder updateItemRequestBuilder(){
        return UpdateItemRequest.builder().id(1L).name("Test item").description("This a new test description").weight(14.3f);
    }

    public static ViewItemResponse.ViewItemResponseBuilder viewItemResponseBuilder(){
        return ViewItemResponse.builder().id(1L).name("Test item").description("This a new test description").weight(16.4f);
    }
}
