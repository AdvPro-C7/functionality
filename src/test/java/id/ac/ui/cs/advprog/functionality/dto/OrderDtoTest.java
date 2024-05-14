package id.ac.ui.cs.advprog.functionality.dto;

import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDtoTest {

    @Test
    public void testOrderDto() {
        // Create sample data
        Long id = 1L;
        Long totalPrice = 1000L;
        String shippingAddress = "123 Main St, City";
        OrderStatus status = OrderStatus.PENDING;
        String username = "john_doe";
        Date orderDate = new Date();

        // Create a CartItemsDto object
        CartItemsDto cartItemsDto1 = new CartItemsDto();
        cartItemsDto1.setId(1L);
        // Add more properties as needed

        CartItemsDto cartItemsDto2 = new CartItemsDto();
        cartItemsDto2.setId(2L);
        // Add more properties as needed

        List<CartItemsDto> cartItems = Arrays.asList(cartItemsDto1, cartItemsDto2);

        // Create an OrderDto object
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setTotalPrice(totalPrice);
        orderDto.setShippingAddress(shippingAddress);
        orderDto.setStatus(status);
        orderDto.setUsername(username);
        orderDto.setOrderDate(orderDate);
        orderDto.setCartItems(cartItems);

        // Test getters
        assertEquals(id, orderDto.getId());
        assertEquals(totalPrice, orderDto.getTotalPrice());
        assertEquals(shippingAddress, orderDto.getShippingAddress());
        assertEquals(status, orderDto.getStatus());
        assertEquals(username, orderDto.getUsername());
        assertEquals(orderDate, orderDto.getOrderDate());
        assertEquals(cartItems, orderDto.getCartItems());

        // Test setters
        Long newId = 2L;
        Long newTotalPrice = 2000L;
        String newShippingAddress = "456 Elm St, City";
        OrderStatus newStatus = OrderStatus.WAITING_PAYMENT;
        String newUsername = "jane_doe";
        Date newOrderDate = new Date();

        orderDto.setId(newId);
        orderDto.setTotalPrice(newTotalPrice);
        orderDto.setShippingAddress(newShippingAddress);
        orderDto.setStatus(newStatus);
        orderDto.setUsername(newUsername);
        orderDto.setOrderDate(newOrderDate);

        // Verify that setters worked correctly
        assertEquals(newId, orderDto.getId());
        assertEquals(newTotalPrice, orderDto.getTotalPrice());
        assertEquals(newShippingAddress, orderDto.getShippingAddress());
        assertEquals(newStatus, orderDto.getStatus());
        assertEquals(newUsername, orderDto.getUsername());
        assertEquals(newOrderDate, orderDto.getOrderDate());
    }
}
