package com.pgl1.database.controller;

import com.pgl1.database.dto.request.CreateItemRequest;
import com.pgl1.database.dto.request.UpdateItemRequest;
import com.pgl1.database.dto.response.ViewItemResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.mockData.ItemTestDataBuilder;
import com.pgl1.database.mockData.LocationTestDataBuilder;
import com.pgl1.database.mockData.UserTestDataBuilder;
import com.pgl1.database.model.entity.Location;
import com.pgl1.database.model.entity.User;
import com.pgl1.database.service.ItemService;
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

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class ItemControllerTest {

    @Mock
    private ItemService itemService;

    private CreateItemRequest createItem;
    private ViewItemResponse viewItem;
    private UpdateItemRequest updateItem;

    @InjectMocks
    private ItemController itemController;

    MockHttpServletRequest mockRequest = new MockHttpServletRequest();

    @BeforeEach
    void setUp() {
        Location testLocation = LocationTestDataBuilder.locationBuilder().build();
        User testUser = UserTestDataBuilder.userBuilder().build();

        createItem = ItemTestDataBuilder.createItemRequestBuilder().user(testUser).build();
        updateItem = ItemTestDataBuilder.updateItemRequestBuilder().user(testUser).build();
        viewItem = ItemTestDataBuilder.viewItemResponseBuilder().build();

        mockRequest.setRequestURI("/items");
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
        assertEquals("/items", response.getBody().getPath());
        assertEquals(viewItem, response.getBody().getData());  // More specific than full object comparison

        verify(itemService).createItem(createItem);
    }


    @Test
    void createItem_ShouldThrowException_WhenRequestIsInvalid() {
        CreateItemRequest invalidRequest = ItemTestDataBuilder.createItemRequestBuilder().build(); // Missing required fields

        doThrow(new ConstraintViolationException(null))
                .when(itemService).createItem(invalidRequest);

        assertThrows(ConstraintViolationException.class, () ->
                itemController.createItem(invalidRequest, mockRequest));
    }

    @Test
    void updateItem_ShouldReturnUpdatedItem() {
        Long itemId = 1L;
        when(itemService.updateItem(any(UpdateItemRequest.class)))
                .thenReturn(viewItem);

        ResponseEntity<GenericAPIResponse<ViewItemResponse>> response =
                itemController.updateItem(updateItem, itemId, mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Item1has been updated", response.getBody().getMessage());
        assertEquals("/items", response.getBody().getPath());
        assertEquals(viewItem, response.getBody().getData());

        verify(itemService).updateItem(updateItem);
    }

    @Test
    void updateItem_ShouldThrowException_WhenItemNotFound() {
        Long nonExistentId = 999L;
        when(itemService.updateItem(any(UpdateItemRequest.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));

        assertThrows(ResponseStatusException.class, () ->
                itemController.updateItem(updateItem, nonExistentId, mockRequest));
    }

    @Test
    void updateItem_ShouldThrowException_WhenRequestIsInvalid() {
        UpdateItemRequest invalidRequest = ItemTestDataBuilder.updateItemRequestBuilder().build(); // Missing required fields

        doThrow(new ConstraintViolationException(null))
                .when(itemService).updateItem(invalidRequest);

        assertThrows(ConstraintViolationException.class, () ->
                itemController.updateItem(invalidRequest, 1L, mockRequest));
    }

    @Test
    void deleteItem_ShouldReturnNoContent() {
        Long itemId = 1L;
        doNothing().when(itemService).deleteItem(itemId);

        ResponseEntity<GenericAPIResponse<Void>> response =
                itemController.deleteItem(itemId, mockRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Item with id: 1has been deleted.", response.getBody().getMessage());
        assertEquals("/items", response.getBody().getPath());
        assertNull(response.getBody().getData());

        verify(itemService).deleteItem(itemId);
    }

    @Test
    void deleteItem_ShouldThrowException_WhenItemNotFound() {
        Long nonExistentId = 999L;
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"))
                .when(itemService).deleteItem(nonExistentId);

        assertThrows(ResponseStatusException.class, () ->
                itemController.deleteItem(nonExistentId, mockRequest));
    }

    @Test
    void deleteItem_ShouldThrowException_WhenIdIsInvalid() {
        Long invalidId = 99999L;

        assertThrows(ConstraintViolationException.class, () ->
                itemController.deleteItem(invalidId, mockRequest));

        verify(itemService, never()).deleteItem(invalidId);
    }

    // Edge case tests
    @Test
    void createItem_ShouldHandleServiceExceptions() {
        when(itemService.createItem(any(CreateItemRequest.class)))
                .thenThrow(new RuntimeException("Service exception"));

        assertThrows(RuntimeException.class, () ->
                itemController.createItem(createItem, mockRequest));
    }

    @Test
    void updateItem_ShouldHandleServiceExceptions() {
        when(itemService.updateItem(any(UpdateItemRequest.class)))
                .thenThrow(new RuntimeException("Service exception"));

        assertThrows(RuntimeException.class, () ->
                itemController.updateItem(updateItem, 1L, mockRequest));
    }

    @Test
    void deleteItem_ShouldHandleServiceExceptions() {
        doThrow(new RuntimeException("Service exception"))
                .when(itemService).deleteItem(1L);

        assertThrows(RuntimeException.class, () ->
                itemController.deleteItem(1L, mockRequest));
    }
}