package com.pgl1.database.controller;

import com.pgl1.database.dto.request.CreateItemRequest;
import com.pgl1.database.dto.request.CreateLocationRequest;
import com.pgl1.database.dto.request.CreateOrderRequest;
import com.pgl1.database.dto.request.UpdateItemRequest;
import com.pgl1.database.dto.response.ViewItemResponse;
import com.pgl1.database.dto.response.ViewLocationResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.mockData.ItemTestDataBuilder;
import com.pgl1.database.mockData.LocationTestDataBuilder;
import com.pgl1.database.mockData.UserTestDataBuilder;
import com.pgl1.database.model.entity.Location;
import com.pgl1.database.model.entity.User;
import com.pgl1.database.service.ItemService;
import com.pgl1.database.util.ResponseUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class ItemControllerTest {

    @Mock
    private ItemService itemService;

    private CreateItemRequest createItem;
    private ViewItemResponse viewItem;

    @InjectMocks
    private ItemController itemController;

    MockHttpServletRequest mockRequest = new MockHttpServletRequest();

    @BeforeEach
    void setUp() {
        //Create a user to associate the item with
        Location testLocation = LocationTestDataBuilder.locationBuilder().build();
        User testUser = UserTestDataBuilder.userBuilder().build();

        createItem = ItemTestDataBuilder.createItemRequestBuilder().user(testUser).build();
        viewItem = ItemTestDataBuilder.viewItemResponseBuilder().build();

        mockRequest.setRequestURI("/orders");
    }

    @Test
    void createItem_ShouldReturnCreatedItem() {
        when(itemService.createItem(any(CreateItemRequest.class)))
                .thenReturn(viewItem);

        ResponseEntity<GenericAPIResponse<ViewItemResponse>> response =
                itemController.createItem(createItem, mockRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("An item has been created", response.getBody().getMessage());
        assertEquals("/orders", response.getBody().getPath());
        assertEquals(viewItem, response.getBody().getData());  // More specific than full object comparison

        verify(itemService).createItem(createItem);
    }


}