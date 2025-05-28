package com.pgl1.database.controller;

import com.pgl1.database.dto.request.CreateItemRequest;
import com.pgl1.database.dto.request.CreatePickupPointRequest;
import com.pgl1.database.dto.request.UpdatePickupPointRequest;
import com.pgl1.database.dto.response.ViewItemResponse;
import com.pgl1.database.dto.response.ViewPickupPointResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.mockData.LocationTestDataBuilder;
import com.pgl1.database.mockData.PickupPointTestDataBuilder;
import com.pgl1.database.model.entity.PickupPoint;
import com.pgl1.database.service.PickupPointService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.pgl1.database.model.entity.Location;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Transactional
class PickupPointControllerTest {

    @Mock
    private PickupPointService pickupPointService;

    @InjectMocks
    private PickupPointController pickupPointController;

    private CreatePickupPointRequest createPickupPoint;
    private UpdatePickupPointRequest updatePickupPoint;
    private ViewPickupPointResponse viewPickupPoint;

    MockHttpServletRequest mockRequest = new MockHttpServletRequest();

    @BeforeEach
    void setUp() {
        Location location = LocationTestDataBuilder.locationBuilder().build();

        createPickupPoint = PickupPointTestDataBuilder.createPickupPointRequestBuilder().build();
        updatePickupPoint = PickupPointTestDataBuilder.updatePickupPointRequestBuilder().build();
        viewPickupPoint = PickupPointTestDataBuilder.viewPickupPointResponseBuilder().build();

        mockRequest.setRequestURI("/pickup-points");
    }

    @Test
    void createPickupPoint_ShouldReturnCreatedResponse() {
        when(pickupPointService.createPickupPoint(any(CreatePickupPointRequest.class)))
                .thenReturn(viewPickupPoint);

        ResponseEntity<GenericAPIResponse<ViewPickupPointResponse>> response =
                pickupPointController.createPickupPoint(createPickupPoint, mockRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("A pickup point has been added", response.getBody().getMessage());
        assertEquals("/pickup-points", response.getBody().getPath());
        assertEquals(viewPickupPoint, response.getBody().getData());  // More specific than full object comparison

        verify(pickupPointService).createPickupPoint(createPickupPoint);
    }

    @Test
    void createPickupPoint_ShouldHandleServiceException() {
        when(pickupPointService.createPickupPoint(any(CreatePickupPointRequest.class)))
                .thenThrow(new RuntimeException("Service exception"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> pickupPointController.createPickupPoint(createPickupPoint, mockRequest));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("Service exception", exception.getReason());
    }

    @Test
    void updatePickupPoint_ShouldReturnUpdatedResponse() {
        Long id = 1L;
        when(pickupPointService.updatePickupPoint(any(UpdatePickupPointRequest.class)))
                .thenReturn(viewPickupPoint);

        ResponseEntity<GenericAPIResponse<ViewPickupPointResponse>> response =
                pickupPointController.updatePickupPoint(updatePickupPoint, id, mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("The pickup point of id 1 has been updated", response.getBody().getMessage());
        assertEquals("/pickup-points", response.getBody().getPath());
        assertEquals(viewPickupPoint, response.getBody().getData());

        verify(pickupPointService).updatePickupPoint(updatePickupPoint);
    }

    @Test
    void updatePickupPoint_ShouldHandleNotFound() {
        Long id = 1L;
        when(pickupPointService.updatePickupPoint(any(UpdatePickupPointRequest.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Pickup point not found"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> pickupPointController.updatePickupPoint(updatePickupPoint, id, mockRequest));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Pickup point not found", exception.getReason());
    }

    @Test
    void deletePickupPoint_ShouldReturnNoContent() {
        Long id = 1L;
        doNothing().when(pickupPointService).deletePickupPoint(id);

        ResponseEntity<GenericAPIResponse<Void>> response =
                pickupPointController.deletePickupPoint(id, mockRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("A pickup point of id 1 has been deleted", response.getBody().getMessage());
        assertEquals("/pickup-points", response.getBody().getPath());
        assertNull(response.getBody().getData());

        verify(pickupPointService).deletePickupPoint(id);
    }

    @Test
    void deletePickupPoint_ShouldHandleNotFound() {
        Long id = 1L;
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Pickup point not found"))
                .when(pickupPointService).deletePickupPoint(id);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> pickupPointController.deletePickupPoint(id, mockRequest));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Pickup point not found", exception.getReason());
    }

    @Test
    void getAllPickupPoints_ShouldReturnListOfPickupPoints() {
        List<ViewPickupPointResponse> pickupPoints = Collections.singletonList(viewPickupPoint);
        when(pickupPointService.fetchAll()).thenReturn(pickupPoints);

        ResponseEntity<GenericAPIResponse<List<ViewPickupPointResponse>>> response =
                pickupPointController.getAllPickupPoints(mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("All pickup points fetched.", response.getBody().getMessage());
        assertEquals("/pickup-points", response.getBody().getPath());
        assertEquals(1, response.getBody().getData().size());
        assertEquals(viewPickupPoint, response.getBody().getData().get(0));

        verify(pickupPointService).fetchAll();
    }

    @Test
    void getAllPickupPoints_ShouldReturnEmptyList() {
        when(pickupPointService.fetchAll()).thenReturn(Collections.emptyList());

        ResponseEntity<GenericAPIResponse<List<ViewPickupPointResponse>>> response =
                pickupPointController.getAllPickupPoints(mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("All pickup points fetched.", response.getBody().getMessage());
        assertEquals("/pickup-points", response.getBody().getPath());
        assertTrue(response.getBody().getData().isEmpty());

        verify(pickupPointService).fetchAll();
    }

    @Test
    void getAllPickupPoints_ShouldHandleServiceException() {
        when(pickupPointService.fetchAll()).thenThrow(new RuntimeException("Database error"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> pickupPointController.getAllPickupPoints(mockRequest));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("Database error", exception.getReason());
    }
}