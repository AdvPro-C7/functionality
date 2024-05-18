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
        double totalPrice = 1000.0;
        String shippingAddress = "123 Main St, City";
        OrderStatus status = OrderStatus.PENDING;
        String username = "john_doe";
        Date orderDate = new Date();

        CartItemsDto cartItemsDto1 = new CartItemsDto();
        cartItemsDto1.setId(1L);

        CartItemsDto cartItemsDto2 = new CartItemsDto();
        cartItemsDto2.setId(2L);


        List<CartItemsDto> cartItems = Arrays.asList(cartItemsDto1, cartItemsDto2);


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
        double newTotalPrice = 2000.0;
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


        assertEquals(newId, orderDto.getId());
        assertEquals(newTotalPrice, orderDto.getTotalPrice());
        assertEquals(newShippingAddress, orderDto.getShippingAddress());
        assertEquals(newStatus, orderDto.getStatus());
        assertEquals(newUsername, orderDto.getUsername());
        assertEquals(newOrderDate, orderDto.getOrderDate());
    }
}
