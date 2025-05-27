package com.pgl1.database.mockData;

import com.pgl1.database.dto.request.CreateLocationRequest;
import com.pgl1.database.dto.request.UpdateLocationRequest;
import com.pgl1.database.dto.response.ViewLocationResponse;
import com.pgl1.database.model.entity.Location;


public class LocationTestDataBuilder {

    public static CreateLocationRequest.CreateLocationRequestBuilder buildCreateLocationRequest(){
        return CreateLocationRequest
                .builder()
                .country("Nepal")
                .city("Kathmandu")
                .street("Kalopul");
    }

    public static UpdateLocationRequest.UpdateLocationRequestBuilder buildUpdateLocationRequest() {
        return UpdateLocationRequest
                .builder()
                .id(1L)
                .country("Nepal")
                .city("Kathmandu")
                .street("Kalopul");
    }

    public static ViewLocationResponse.ViewLocationResponseBuilder buildViewLocationResponse() {
        return ViewLocationResponse
                .builder()
                .id(1L)
                .country("Nepal")
                .city("Kathmandu")
                .street("Kalopul");
    }

    public static Location.LocationBuilder locationBuilder(){
        return Location
                .builder()
                .id(1L)
                .country("Nepal")
                .city("Kathmandu")
                .street("Kalopul");
    }
}