package com.pgl1.database.controller;

import com.pgl1.database.dto.request.ItemCreateDTO;
import com.pgl1.database.dto.request.ItemUpdateDTO;
import com.pgl1.database.dto.response.ItemViewDTO;
import com.pgl1.database.model.entity.Location;
import com.pgl1.database.service.ItemService;
import com.pgl1.database.model.entity.User;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    private ItemCreateDTO itemCreateDTO;
    private ItemUpdateDTO itemUpdateDTO;
    private ItemViewDTO itemViewDTO;

    @BeforeEach
    void setUp() {
        Location location = Location.builder().country("Nepal").city("Kathmandu").street("Kalopul").build();
        User user = User.builder().id(1L).name("Tester").phone("9876543210").email("test@test.com").location(location).build();

        itemCreateDTO = ItemCreateDTO.builder().user(user).name("Furniture").description("From IKEA").weight(1.2f).build();
        itemUpdateDTO = ItemUpdateDTO.builder().user(user).name("Furniture").description("Changed: From Costco").weight(1.2f).build();
        itemViewDTO = ItemViewDTO.builder().id(1L).name("Furniture").description("Changed: From Costco").weight(1.2f).localDateTime(LocalDateTime.now()).build();
    }

    @Test
    void createItem_ShouldReturnCreatedStatusAndItem() {
        when(itemService.createItem(any(ItemCreateDTO.class))).thenReturn(itemViewDTO);

        ResponseEntity<ItemViewDTO> response = itemController.createItem(itemCreateDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(itemViewDTO, response.getBody());
        verify(itemService, times(1)).createItem(itemCreateDTO);
    }

    @Test
    void updateItem_ShouldReturnOkStatusAndUpdatedItem() {
        when(itemService.updateItem(any(ItemUpdateDTO.class))).thenReturn(itemViewDTO);

        ResponseEntity<ItemViewDTO> response = itemController.updateItem(itemUpdateDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(itemViewDTO, response.getBody());
        verify(itemService, times(1)).updateItem(itemUpdateDTO);
    }

    @Test
    void deleteItem_ShouldReturnNoContentStatus() {
        Integer itemId = 1;
        doNothing().when(itemService).deleteItem(itemId);

        ResponseEntity<Void> response = itemController.deleteItem(itemId);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(itemService, times(1)).deleteItem(itemId);
    }

    @Test
    void createItem_ShouldHandleServiceException() {
        when(itemService.createItem(any(ItemCreateDTO.class)))
                .thenThrow(new RuntimeException("Service exception"));

        assertThrows(RuntimeException.class, () -> itemController.createItem(itemCreateDTO));
        verify(itemService, times(1)).createItem(itemCreateDTO);
    }

    @Test
    void updateItem_ShouldHandleServiceException() {
        when(itemService.updateItem(any(ItemUpdateDTO.class)))
                .thenThrow(new RuntimeException("Service exception"));

        assertThrows(RuntimeException.class, () -> itemController.updateItem(itemUpdateDTO));
        verify(itemService, times(1)).updateItem(itemUpdateDTO);
    }

    @Test
    void deleteItem_ShouldHandleServiceException() {
        Integer itemId = 1;
        doThrow(new RuntimeException("Service exception")).when(itemService).deleteItem(itemId);

        assertThrows(RuntimeException.class, () -> itemController.deleteItem(itemId));
        verify(itemService, times(1)).deleteItem(itemId);
    }

    @Test
    void createItem_ShouldValidateInput() {
        ItemCreateDTO invalidItem = ItemCreateDTO.builder().build();

        assertThrows(Exception.class, () -> itemController.createItem(invalidItem));
        verify(itemService, never()).createItem(any());
    }

    @Test
    void updateItem_ShouldValidateInput() {
        ItemUpdateDTO invalidItem = ItemUpdateDTO.builder().build();

        assertThrows(Exception.class, () -> itemController.updateItem(invalidItem));
        verify(itemService, never()).updateItem(any());
    }
}