package id.ac.ui.cs.advprog.functionality.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CartItemTest {
    private Book book;

    @BeforeEach
    public void setUp() {
        Book book = new Book("title", "author", 100);
    }
    
    @Test
    public void testCreateCartItemWithQuantity() {
        Integer quantity = 3;
        CartItem cartItem = new CartItem(book,quantity);
        assertEquals(quantity, cartItem.getQuantity());
    }

    @Test
    public void testIncreaseQuantity() {
        CartItem cartItem = new CartItem();
        cartItem.increaseQuantity();
        assertEquals(1, cartItem.getQuantity());
    }

    @Test
    public void testDecreaseQuantity() {
        CartItem cartItem = new CartItem(book,3);
        cartItem.decreaseQuantity();
        assertEquals(2, cartItem.getQuantity());
    }



}
