package id.ac.ui.cs.advprog.functionality.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.CartItem;

@DataJpaTest(showSql = true)
public class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(20000); 
    }

    @Test
    public void testSaveCartItem() {
        CartItem cartItem = new CartItem(book, 1);

        cartItemRepository.save(cartItem);
        CartItem savedCartItem = cartItemRepository.findById(cartItem.getId()).orElse(null);

        assertNotNull(savedCartItem);
        assertEquals(cartItem.getBook(), savedCartItem.getBook());
        assertEquals(cartItem.getQuantity(), savedCartItem.getQuantity());
    }

    @Test
    public void testIncreaseQuantity() {
        CartItem cartItem = new CartItem(book, 1);
        cartItemRepository.save(cartItem);

        cartItem.increaseQuantity();
        CartItem updatedCartItem = cartItemRepository.findById(cartItem.getId()).orElse(null);

        assertNotNull(updatedCartItem);
        assertEquals(2, updatedCartItem.getQuantity());
    }

    @Test
    public void testDecreaseQuantity() {
        CartItem cartItem = new CartItem(book, 2);
        cartItemRepository.save(cartItem);

        cartItem.decreaseQuantity();
        CartItem updatedCartItem = cartItemRepository.findById(cartItem.getId()).orElse(null);

        assertNotNull(updatedCartItem);
        assertEquals(1, updatedCartItem.getQuantity());
    }

    @Test
    public void testDeleteCartItem() {
        CartItem cartItem = new CartItem(book, 1);
        cartItemRepository.save(cartItem);

        cartItemRepository.delete(cartItem);
        CartItem deletedCartItem = cartItemRepository.findById(cartItem.getId()).orElse(null);

        assertEquals(null, deletedCartItem);
    }

}
