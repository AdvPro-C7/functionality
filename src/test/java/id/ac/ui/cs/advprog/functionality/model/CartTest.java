package id.ac.ui.cs.advprog.functionality.model;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import jakarta.persistence.*;

public class CartTest {

    @Test
    public void testCreateCart() {
        Cart cart = new Cart();
        assertNotNull(cart);
    }

    @Test
    public void testCreateCartWithUser() {
        User user = new User();
        Cart cart = new Cart(user);
        assertNotNull(cart);
    }

    @Test
    public void testGetCartItems() {
        Cart cart = new Cart();
        assertNull(cart.getCartItems());
    }

    
}
