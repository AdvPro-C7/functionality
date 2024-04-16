package id.ac.ui.cs.advprog.functionality.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {
    
    @Test
    public void testCreateCartItemWithQuantity() {
        int quantity = 3;
        CartItem cartItem = new CartItem(quantity);
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
        CartItem cartItem = new CartItem(3);
        cartItem.decreaseQuantity();
        assertEquals(2, cartItem.getQuantity());
    }



}
