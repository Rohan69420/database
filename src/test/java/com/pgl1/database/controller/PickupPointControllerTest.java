package com.pgl1.database.controller;

import com.pgl1.database.dto.request.PickupPointCreateDTO;
import com.pgl1.database.dto.request.PickupPointUpdateDTO;
import com.pgl1.database.dto.response.PickupPointViewDTO;
import com.pgl1.database.service.PickupPointService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.pgl1.database.model.entity.Location;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PickupPointControllerTest {

    @Mock
    private PickupPointService pickupPointService;

    @InjectMocks
    private PickupPointController pickupPointController;

    private PickupPointCreateDTO createDTO;
    private PickupPointUpdateDTO updateDTO;
    private PickupPointViewDTO viewDTO;

    @BeforeEach
    void setUp() {
        Location location = Location.builder().country("Nepal").city("Bhaktapur").street("Thimi").build();

        createDTO = PickupPointCreateDTO.builder().name("Thimi branch").contact("0123456789").location(location).description("Only pickup point in bkt").build();

        updateDTO = PickupPointUpdateDTO.builder().id(1L).name("Khowpa").contact("9876543210").location(location).description("Update: Shifted from Thimi to Khwopa").build();

        viewDTO = PickupPointViewDTO.builder().id(1L).name("Khowpa").contact("9876543210").location(location).description("Update: Shifted from Thimi to Khwopa").build();

    }

    @Test
    void createPickupPoint_ShouldReturnCreatedResponse() {
        when(pickupPointService.createPickupPoint(any(PickupPointCreateDTO.class))).thenReturn(viewDTO);

        ResponseEntity<PickupPointViewDTO> response = pickupPointController.createPickupPoint(createDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(viewDTO, response.getBody());

        verify(pickupPointService, times(1)).createPickupPoint(createDTO);
    }

    @Test
    void updatePickupPoint_ShouldReturnOkResponse() {
        when(pickupPointService.updatePickupPoint(any(PickupPointUpdateDTO.class))).thenReturn(viewDTO);

        ResponseEntity<PickupPointViewDTO> response = pickupPointController.updatePickupPoint(updateDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(viewDTO, response.getBody());

        verify(pickupPointService, times(1)).updatePickupPoint(updateDTO);
    }

    @Test
    void deletePickupPoint_ShouldReturnNoContentResponse() {
        Integer pickupPointId = 1;
        doNothing().when(pickupPointService).deletePickupPoint(pickupPointId);

        ResponseEntity<Void> response = pickupPointController.deletePickupPoint(pickupPointId);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(pickupPointService, times(1)).deletePickupPoint(pickupPointId);
    }

    @Test
    void createPickupPoint_ShouldHandleServiceException() {
        when(pickupPointService.createPickupPoint(any(PickupPointCreateDTO.class)))
                .thenThrow(new RuntimeException("Service exception"));

        assertThrows(RuntimeException.class, () -> {
            pickupPointController.createPickupPoint(createDTO);
        });
    }

    @Test
    void updatePickupPoint_ShouldHandleInvalidInput() {
        PickupPointUpdateDTO invalidDTO = PickupPointUpdateDTO.builder().build();
        when(pickupPointService.updatePickupPoint(invalidDTO))
                .thenThrow(new IllegalArgumentException("Invalid input"));

        assertThrows(IllegalArgumentException.class, () -> {
            pickupPointController.updatePickupPoint(invalidDTO);
        });
    }

    @Test
    void deletePickupPoint_ShouldHandleNotFound() {
        Integer nonExistentId = 999;
        doThrow(new RuntimeException("Pickup point not found"))
                .when(pickupPointService).deletePickupPoint(nonExistentId);

        assertThrows(RuntimeException.class, () -> {
            pickupPointController.deletePickupPoint(nonExistentId);
        });
    }
}