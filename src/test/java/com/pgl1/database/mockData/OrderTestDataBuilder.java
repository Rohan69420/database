package com.pgl1.database.mockData;

import com.pgl1.database.dto.request.CreateOrderRequest;
import com.pgl1.database.dto.request.UpdateOrderRequest;
import com.pgl1.database.dto.response.ViewOrderResponse;

public class OrderTestDataBuilder {
    public static CreateOrderRequest.CreateOrderRequestBuilder createOrderRequestBuilder(){
        return CreateOrderRequest
                .builder()
                .name("Test order")
                .type("FeDEX");
    }

    public static UpdateOrderRequest.UpdateOrderRequestBuilder updateOrderRequestBuilder(){
        return UpdateOrderRequest
                .builder()
                .id(1L)
                .name("Test order")
                .type("FeDEX");
    }

    public static ViewOrderResponse.ViewOrderResponseBuilder viewOrderResponseBuilder(){
        return ViewOrderResponse
                .builder()
                .id(1L)
                .name("Test order")
                .type("FeDEX");
    }
}
