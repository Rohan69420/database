package com.pgl1.database.dto.request;

import com.pgl1.database.model.entity.PickupPoint;
import com.pgl1.database.model.entity.Item;
import com.pgl1.database.enums.OrderStatus;
import com.pgl1.database.model.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class CreateOrderRequest {
    @NotBlank(message = "Please specify an order name")
    @Size(max=20, message="Order name should be shorter than 20 characters")
    private String name;

    @NotNull(message = "Please associate at least one item for this order")
    private List<Item> items;

    private User user;

    @NotNull(message = "Please specify the source address")
    private PickupPoint source;

    @NotNull(message = "Please specify the destination address")
    private PickupPoint destination;

    @NotEmpty(message = "Please specify the order type")
    private String type;

    private OrderStatus status;
}