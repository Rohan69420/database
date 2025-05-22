package com.pgl1.database.controller;

import com.pgl1.database.dto.request.LocationCreateDTO;
import com.pgl1.database.dto.request.LocationUpdateDTO;
import com.pgl1.database.dto.response.LocationViewDTO;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.pgl1.database.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    private LocationCreateDTO locationCreateDTO;
    private LocationUpdateDTO locationUpdateDTO;
    private LocationViewDTO locationViewDTO;

    @BeforeEach
    void setUp() {
        // Initialize test data
        locationCreateDTO = new LocationCreateDTO(
                "Nepal",
                "Kathmandu",
                "Kalopul"
        );

        locationUpdateDTO = new LocationUpdateDTO(
                1L,
                "Nepal",
                "Kathmandu",
                "Chabahil"
        );

        locationViewDTO = new LocationViewDTO(
                1L,
                "Nepal",
                "Kathmandu",
                "Chabahil"
        );
    }

    @Test
    void createLocation_ShouldReturnCreatedStatusAndLocation() {
        when(locationService.createLocation(any(LocationCreateDTO.class))).thenReturn(locationViewDTO);

        ResponseEntity<LocationViewDTO> response = locationController.createLocation(locationCreateDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(locationViewDTO, response.getBody());

        verify(locationService, times(1)).createLocation(locationCreateDTO);
    }

    @Test
    void updateLocation_ShouldReturnOkStatusAndUpdatedLocation() {
        when(locationService.updateLocation(any(LocationUpdateDTO.class))).thenReturn(locationViewDTO);

        ResponseEntity<LocationViewDTO> response = locationController.updateLocation(locationUpdateDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(locationViewDTO, response.getBody());

        verify(locationService, times(1)).updateLocation(locationUpdateDTO);
    }

    @Test
    void deleteLocation_ShouldReturnNoContentStatus() {
        Integer locationId = 1;
        doNothing().when(locationService).deleteLocation(locationId);

        ResponseEntity<Void> response = locationController.deleteLocation(locationId);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(locationService, times(1)).deleteLocation(locationId);
    }

    @Test
    void createLocation_ShouldHandleServiceException() {
        when(locationService.createLocation(any(LocationCreateDTO.class)))
                .thenThrow(new RuntimeException("Service exception"));

        assertThrows(RuntimeException.class, () -> {
            locationController.createLocation(locationCreateDTO);
        });
    }

    @Test
    void updateLocation_ShouldHandleServiceException() {
        when(locationService.updateLocation(any(LocationUpdateDTO.class)))
                .thenThrow(new RuntimeException("Service exception"));

        assertThrows(RuntimeException.class, () -> {
            locationController.updateLocation(locationUpdateDTO);
        });
    }

    @Test
    void deleteLocation_ShouldHandleServiceException() {
        Integer locationId = 1;
        doThrow(new RuntimeException("Service exception")).when(locationService).deleteLocation(locationId);

        assertThrows(RuntimeException.class, () -> {
            locationController.deleteLocation(locationId);
        });
    }
}