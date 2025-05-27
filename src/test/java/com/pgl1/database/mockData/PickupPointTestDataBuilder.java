package com.pgl1.database.mockData;

import com.pgl1.database.dto.request.CreatePickupPointRequest;
import com.pgl1.database.dto.request.UpdatePickupPointRequest;
import com.pgl1.database.dto.response.ViewPickupPointResponse;

public class PickupPointTestDataBuilder {

    public static CreatePickupPointRequest.CreatePickupPointRequestBuilder createPickupPointRequestBuilder(){
        return CreatePickupPointRequest
                .builder()
                .name("Test pickup location")
                .contact("9876543210")
                .description("This is the test description");
    }

    public static UpdatePickupPointRequest.UpdatePickupPointRequestBuilder updatePickupPointRequestBuilder(){
        return UpdatePickupPointRequest
                .builder()
                .id(1L)
                .name("Test pickup location")
                .contact("9876543210")
                .description("This is the test description");
    }

    public static ViewPickupPointResponse.ViewPickupPointResponseBuilder viewPickupPointResponseBuilder(){
        return ViewPickupPointResponse
                .builder()
                .id(1L)
                .name("Test pickup location")
                .contact("9876543210")
                .description("This is the test description");
    }
}
