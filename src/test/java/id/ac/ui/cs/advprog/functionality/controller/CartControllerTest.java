package id.ac.ui.cs.advprog.functionality.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.functionality.dto.*;
import id.ac.ui.cs.advprog.functionality.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    CartController cartController;

    @Mock
    CartService cartService;

    private JacksonTester<AddBookCartDto> jsonBookCartDto;
    private JacksonTester<UserDto> jsonUserDto;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    void testAddProductToCart() throws Exception {
        AddBookCartDto addBookCartDto = new AddBookCartDto();
        when(cartService.addBookToCart(any())).thenReturn(ResponseEntity.ok().build());

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/customer/cart")
                                .contentType("application/json")
                                .content(jsonBookCartDto.write(addBookCartDto).getJson()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testCreateCart() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserId(1L);
        when(cartService.createCart(userDto.getUserId())).thenReturn(ResponseEntity.ok().build());

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/customer/createCart")
                                .contentType("application/json")
                                .content(jsonUserDto.write(userDto).getJson()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testGetCartByUserId() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserId(1L);
        when(cartService.getCartByUserId(userDto.getUserId())).thenReturn(ResponseEntity.ok().build());

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/customer/userCart")
                                .contentType("application/json")
                                .content(jsonUserDto.write(userDto).getJson()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testIncreaseBookQuantity() throws Exception {
        AddBookCartDto addBookCartDto = new AddBookCartDto();
        when(cartService.increaseProductQuantity(any())).thenReturn(ResponseEntity.ok().build());

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/customer/addition")
                                .contentType("application/json")
                                .content(jsonBookCartDto.write(addBookCartDto).getJson()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testDecreaseBookQuantity() throws Exception {
        AddBookCartDto addBookCartDto = new AddBookCartDto();
        when(cartService.decreaseProductQuantity(any())).thenReturn(ResponseEntity.ok().build());

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/customer/deduction")
                                .contentType("application/json")
                                .content(jsonBookCartDto.write(addBookCartDto).getJson()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testDeleteCartItem() throws Exception {
        Long cartItemId = 1L;
        when(cartService.deleteCartItem(anyLong())).thenReturn(ResponseEntity.ok().build());

        MockHttpServletResponse response = mockMvc.perform(
                        delete("/api/customer/cart/" + cartItemId))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}