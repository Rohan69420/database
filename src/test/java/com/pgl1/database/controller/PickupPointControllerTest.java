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
}