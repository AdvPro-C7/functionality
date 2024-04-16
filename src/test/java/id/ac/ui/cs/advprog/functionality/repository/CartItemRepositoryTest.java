package id.ac.ui.cs.advprog.functionality.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.CartItem;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@DataJpaTest(showSql = true)
public class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    private CartItem cartItem;

    private Cart mockCart;

    @BeforeEach
    public void setUp() {
        Book book = new Book("title", "author", 100);
        cartItem = new CartItem(book, 3);
        cartItemRepository.save(cartItem);
    }

    @Test
    public void testFindById() {
        Optional<CartItem> cartItem = cartItemRepository.findById(this.cartItem.getId());
        assertTrue(cartItem.isPresent());
        assertEquals(this.cartItem.getId(), cartItem.get().getId());
    }

    @Test
    public void testFindByBook() {
        Optional<CartItem> cartItem = cartItemRepository.findByBook(this.cartItem.getBook());
        assertTrue(cartItem.isPresent());
        assertEquals(this.cartItem.getBook(), cartItem.get().getBook());
    }

    @Test
    public void testFindByQuantity() {
        Optional<CartItem> cartItem = cartItemRepository.findByQuantity(this.cartItem.getQuantity());
        assertTrue(cartItem.isPresent());
        assertEquals(this.cartItem.getQuantity(), cartItem.get().getQuantity());
    }

    @Test
    public void testFindByCart() {
        Optional<CartItem> cartItem = cartItemRepository.findByCart(this.cartItem.getCart());
        assertTrue(cartItem.isPresent());
        assertEquals(this.cartItem.getCart(), cartItem.get().getCart());
    }

    @Test
    public void testDeleteCartItem() {
        cartItemRepository.delete(cartItem);
        Optional<CartItem> cartItem = cartItemRepository.findById(this.cartItem.getId());
        assertFalse(cartItem.isPresent());
    }

    
}
