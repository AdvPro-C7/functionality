package id.ac.ui.cs.advprog.functionality.model;

import id.ac.ui.cs.advprog.functionality.dto.CartItemsDto;
import id.ac.ui.cs.advprog.functionality.dto.OrderDto;
import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderTest {
    @Mock
    private User user;

    @Mock
    private CartItems cartItem;

    @Mock
    private Book book;

    private Order order;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        when(user.getNama()).thenReturn("Test User");
        when(cartItem.getId()).thenReturn(1L);
        when(cartItem.getPrice()).thenReturn(100L);
        when(cartItem.getQuantity()).thenReturn(2L);
        when(cartItem.getBook()).thenReturn(book);
        when(cartItem.getUser()).thenReturn(user);
        order = new Order();
        when(cartItem.getOrder()).thenReturn(order);
        when(cartItem.getOrder()).thenReturn(order);
        when(book.getId()).thenReturn(1L);
        when(book.getTitle()).thenReturn("Test Book");
        when(book.getCoverPicture()).thenReturn("Test Image");

        order = new Order();
        order.setId(1L);
        order.setTotalPrice(200L);
        order.setShippingAddress("Test Address");
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(new Date());
        order.setUser(user);
        order.setCartItems(List.of(cartItem));
    }

    @Test
    public void testGetOrderDto() {
        OrderDto orderDto = order.getOrderDto();

        assertEquals(order.getId(), orderDto.getId());
        assertEquals(order.getTotalPrice(), orderDto.getTotalPrice());
        assertEquals(order.getShippingAddress(), orderDto.getShippingAddress());
        assertEquals(order.getStatus(), orderDto.getStatus());
        assertEquals(order.getUser().getNama(), orderDto.getUsername());
        assertEquals(order.getOrderDate(), orderDto.getOrderDate());

        List<CartItemsDto> cartItemsDtos = orderDto.getCartItems();
        assertEquals(1, cartItemsDtos.size());

        CartItemsDto cartItemsDto = cartItemsDtos.getFirst();
        assertEquals(cartItem.getId(), cartItemsDto.getId());
        assertEquals(cartItem.getPrice(), cartItemsDto.getPrice());
        assertEquals(cartItem.getQuantity(), cartItemsDto.getQuantity());
        assertEquals(cartItem.getBook().getId(), cartItemsDto.getBookId());
        assertEquals(cartItem.getOrder().getId(), cartItemsDto.getOrderId());
        assertEquals(cartItem.getBook().getTitle(), cartItemsDto.getBookName());
        assertEquals(cartItem.getBook().getCoverPicture(), cartItemsDto.getReturnedImg());
        assertEquals(cartItem.getUser().getId(), cartItemsDto.getUserId());
    }

    @Test
    public void testCartItems() {
        List<CartItems> testCartItems = Arrays.asList(cartItem);
        order.setCartItems(testCartItems);

        assertEquals(testCartItems, order.getCartItems());
    }

}
