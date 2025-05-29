package com.pgl1.database.controller;


import com.pgl1.database.dto.request.CreateOrderRequest;
import com.pgl1.database.dto.request.UpdateOrderRequest;
import com.pgl1.database.dto.response.ViewOrderResponse;
import com.pgl1.database.mockData.OrderTestDataBuilder;

@ExtendWith(MockitoExtension.class)
@Transactional
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private CreateOrderRequest createOrder;
    private UpdateOrderRequest updateOrder;
    private ViewOrderResponse viewOrder;

    MockHttpServletRequest mockRequest = new MockHttpServletRequest();

    @BeforeEach
    void setUp() {
        createOrder = OrderTestDataBuilder.createOrderRequestBuilder().build();
        updateOrder = OrderTestDataBuilder.updateOrderRequestBuilder().build();
        viewOrder = OrderTestDataBuilder.viewOrderResponseBuilder().build();

        mockRequest.setRequestURI("/orders");
    }


    @Test
    void createLocation_ShouldReturnCreatedResponse() {
        when(orderService.createOrder(any(CreateOrderRequest.class)))
                .thenReturn(viewOrder);

        ResponseEntity<GenericAPIResponse<ViewOrderResponse>> response =
                orderController.createLocation(createOrder, mockRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Location created successfully", response.getBody().getMessage());
        assertEquals("/locations", response.getBody().getPath());
        assertEquals(viewLocation, response.getBody().getData());

        verify(locationService).createLocation(createLocation);
    }

