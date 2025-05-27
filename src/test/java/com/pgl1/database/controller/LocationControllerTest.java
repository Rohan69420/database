package com.pgl1.database.controller;

import com.pgl1.database.dto.request.CreateLocationRequest;
import com.pgl1.database.dto.request.UpdateLocationRequest;
import com.pgl1.database.dto.response.ViewLocationResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.mockData.LocationTestDataBuilder;
import com.pgl1.database.util.ResponseUtil;
import jakarta.transaction.Transactional;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.pgl1.database.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    private CreateLocationRequest createLocation;
    private UpdateLocationRequest updateLocation;
    private ViewLocationResponse viewLocation;

    MockHttpServletRequest mockRequest = new MockHttpServletRequest();

    @BeforeEach
    void setUp() {
        createLocation = LocationTestDataBuilder.buildCreateLocationRequest().build();
        updateLocation = LocationTestDataBuilder.buildUpdateLocationRequest().build();
        viewLocation = LocationTestDataBuilder.buildViewLocationResponse().build();

        mockRequest.setRequestURI("/locations");
    }


    @Test
    void createLocation_ShouldReturnCreatedResponse() {
        when(locationService.createLocation(any(CreateLocationRequest.class)))
                .thenReturn(viewLocation);

        ResponseEntity<GenericAPIResponse<ViewLocationResponse>> response =
                locationController.createLocation(createLocation, mockRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Location created successfully", response.getBody().getMessage());
        assertEquals("/locations", response.getBody().getPath());
        assertEquals(viewLocation, response.getBody().getData());  // More specific than full object comparison

        verify(locationService).createLocation(createLocation);  // Verify correct parameter was passed
    }

    @Test
    void createLocation_withDifferentStreet_shouldWork() {
        CreateLocationRequest customRequest = LocationTestDataBuilder.buildCreateLocationRequest()
                .street("New Street")
                .build();

        ViewLocationResponse customResponse = LocationTestDataBuilder.buildViewLocationResponse()
                .street("New Street")
                .build();

        when(locationService.createLocation(any(CreateLocationRequest.class))).thenReturn(customResponse);

        ResponseEntity<GenericAPIResponse<ViewLocationResponse>> response =
                locationController.createLocation(customRequest, mockRequest);

        assertEquals("New Street", response.getBody().getData().getStreet());
    }
}
