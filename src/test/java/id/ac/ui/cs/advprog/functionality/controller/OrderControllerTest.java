package id.ac.ui.cs.advprog.functionality.controller;

import id.ac.ui.cs.advprog.functionality.dto.*;
import id.ac.ui.cs.advprog.functionality.model.Order;
import id.ac.ui.cs.advprog.functionality.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class OrderControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    private JacksonTester<PlaceOrderDto> jsonPlaceOrderDto;
    private JacksonTester<PaymentDto> jsonPaymentDto;
    private JacksonTester<UserDto> jsonUserDto;
    private JacksonTester<List<Order>> jsonOrderList;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testPlaceOrder() throws Exception {
        PlaceOrderDto placeOrderDto = new PlaceOrderDto();
        when(orderService.placeOrder(any())).thenReturn(ResponseEntity.ok().build());

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/order/placeOrder")
                                .contentType("application/json")
                                .content(jsonPlaceOrderDto.write(placeOrderDto).getJson()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testPayForOrder() throws Exception {
        PaymentDto paymentDto = new PaymentDto();
        when(orderService.payForOrder(any())).thenReturn(ResponseEntity.ok().build());

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/order/pay")
                                .contentType("application/json")
                                .content(jsonPaymentDto.write(paymentDto).getJson()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testCancelOrder() throws Exception {
        PaymentDto paymentDto = new PaymentDto();
        when(orderService.cancelOrder(any())).thenReturn(ResponseEntity.ok().build());

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/order/cancel")
                                .contentType("application/json")
                                .content(jsonPaymentDto.write(paymentDto).getJson()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testGetOrdersWaitingShipping() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserId(1L);
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> orderList = Arrays.asList(order1, order2);
        when(orderService.getOrdersWaitingShipping(userDto.getUserId())).thenReturn(orderList);

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/order/waiting-shipping")
                                .contentType("application/json")
                                .content(jsonUserDto.write(userDto).getJson()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonOrderList.write(orderList).getJson(), response.getContentAsString());
    }

 @Test
void testGetOrdersWaitingPayment() throws Exception {
    UserDto userDto = new UserDto();
    userDto.setUserId(1L);
    Order order1 = new Order();
    Order order2 = new Order();
    List<Order> orderList = Arrays.asList(order1, order2);
    when(orderService.getOrdersWaitingPayment(userDto.getUserId())).thenReturn(orderList);

    MockHttpServletResponse response = mockMvc.perform(
            post("/api/order/waiting-payment")
                    .contentType("application/json")
                    .content(jsonUserDto.write(userDto).getJson()))
            .andReturn().getResponse();

    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertEquals(jsonOrderList.write(orderList).getJson(), response.getContentAsString());
}
}