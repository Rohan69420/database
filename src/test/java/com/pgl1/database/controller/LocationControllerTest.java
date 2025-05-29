package com.pgl1.database.controller;

import com.pgl1.database.dto.request.CreateLocationRequest;
import com.pgl1.database.dto.request.UpdateLocationRequest;
import com.pgl1.database.dto.response.ViewLocationResponse;
import com.pgl1.database.handler.GenericAPIResponse;
import com.pgl1.database.mockData.LocationTestDataBuilder;
import com.pgl1.database.util.ResponseUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
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
        assertEquals(viewLocation, response.getBody().getData());

        verify(locationService).createLocation(createLocation);
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

    @Test
    void createLocation_shouldCallServiceWithCorrectParameters() {
        when(locationService.createLocation(any(CreateLocationRequest.class))).thenReturn(viewLocation);

        locationController.createLocation(createLocation, mockRequest);

        verify(locationService).createLocation(createLocation);
    }

    @Test
    void createLocation_shouldReturnCorrectURIInResponse() {
        when(locationService.createLocation(any(CreateLocationRequest.class))).thenReturn(viewLocation);
        mockRequest.setRequestURI("/api/v1/locations");

        ResponseEntity<GenericAPIResponse<ViewLocationResponse>> response =
                locationController.createLocation(createLocation, mockRequest);

        assertEquals("/api/v1/locations", response.getBody().getPath());
    }

    // Update Location Tests
    @Test
    void updateLocation_shouldReturnOkResponse() {
        when(locationService.updateLocation(any(UpdateLocationRequest.class))).thenReturn(viewLocation);

        ResponseEntity<GenericAPIResponse<ViewLocationResponse>> response =
                locationController.updateLocation(updateLocation, 1, mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Location updated successfully", response.getBody().getMessage());
        assertEquals("/locations", response.getBody().getPath());
        assertEquals(viewLocation, response.getBody().getData());
    }

    @Test
    void updateLocation_shouldCallServiceWithCorrectParameters() {
        when(locationService.updateLocation(any(UpdateLocationRequest.class))).thenReturn(viewLocation);

        locationController.updateLocation(updateLocation, 1, mockRequest);

        verify(locationService).updateLocation(updateLocation);
    }

    @Test
    void updateLocation_withDifferentId_shouldWork() {
        ViewLocationResponse customResponse = LocationTestDataBuilder.buildViewLocationResponse()
                .id(999L)
                .build();

        when(locationService.updateLocation(any(UpdateLocationRequest.class))).thenReturn(customResponse);

        ResponseEntity<GenericAPIResponse<ViewLocationResponse>> response =
                locationController.updateLocation(updateLocation, 999, mockRequest);

        assertEquals(999L, response.getBody().getData().getId());
    }

    @Test
    void updateLocation_shouldIgnorePathIdParameter() {
        // The controller doesn't use the path ID parameter in the service call
        // This test verifies that behavior
        when(locationService.updateLocation(any(UpdateLocationRequest.class))).thenReturn(viewLocation);

        locationController.updateLocation(updateLocation, 999, mockRequest);

        // Verify that the service was called with the request object, not the path ID
        verify(locationService).updateLocation(updateLocation);
    }

    // Delete Location Tests
    @Test
    void deleteLocation_shouldReturnNoContentResponse() {
        doNothing().when(locationService).deleteLocation(anyLong());

        ResponseEntity<GenericAPIResponse<Void>> response =
                locationController.deleteLocation(1L, mockRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Location deleted successfully", response.getBody().getMessage());
        assertEquals("/locations", response.getBody().getPath());
        assertNull(response.getBody().getData());

        verify(locationService).deleteLocation(1L);
    }

    @Test
    void deleteLocation_shouldCallServiceWithCorrectId() {
        doNothing().when(locationService).deleteLocation(anyLong());

        locationController.deleteLocation(123L, mockRequest);

        verify(locationService).deleteLocation(eq(123L));
    }

    @Test
    void deleteLocation_withDifferentIds_shouldWork() {
        doNothing().when(locationService).deleteLocation(anyLong());

        ResponseEntity<GenericAPIResponse<Void>> response1 =
                locationController.deleteLocation(1L, mockRequest);
        ResponseEntity<GenericAPIResponse<Void>> response2 =
                locationController.deleteLocation(999L, mockRequest);

        assertEquals(HttpStatus.NO_CONTENT, response1.getStatusCode());
        assertEquals(HttpStatus.NO_CONTENT, response2.getStatusCode());
        verify(locationService).deleteLocation(1L);
        verify(locationService).deleteLocation(999L);
    }

    // Edge Cases and Error Scenarios
    @Test
    void createLocation_withNullRequest_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> {
            locationController.createLocation(null, mockRequest);
        });
    }

    @Test
    void updateLocation_withNullRequest_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> {
            locationController.updateLocation(null, 1, mockRequest);
        });
    }

    @Test
    void deleteLocation_withNullRequest_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> {
            locationController.deleteLocation(null, mockRequest);
        });
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1})
    void deleteLocation_withInvalidIds_shouldStillCallService(long invalidId) {
        doNothing().when(locationService).deleteLocation(anyLong());

        ResponseEntity<GenericAPIResponse<Void>> response =
                locationController.deleteLocation(invalidId, mockRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(locationService).deleteLocation(eq(invalidId));
    }
}
