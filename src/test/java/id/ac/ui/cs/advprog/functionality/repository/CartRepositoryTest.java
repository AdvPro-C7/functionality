package id.ac.ui.cs.advprog.functionality.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.Cart;
import id.ac.ui.cs.advprog.functionality.model.CartItem;

@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testSaveCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        Cart savedCart = cartRepository.findById(cart.getId()).orElse(null);

        assertNotNull(savedCart);
    }

    @Test
    public void testFindCartById() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        Cart savedCart = cartRepository.findById(cart.getId()).orElse(null);

        assertNotNull(savedCart);
        assertEquals(cart.getId(), savedCart.getId());
    }

    @Test
    public void testDeleteCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        cartRepository.delete(cart);
        Cart deletedCart = cartRepository.findById(cart.getId()).orElse(null);

        assertEquals(null, deletedCart);
    }

    @Test
    public void testCartItemAddedToCart() {

        Cart cart = new Cart();
        cartRepository.save(cart);

        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(20000);
        
        CartItem cartItem = new CartItem(book,1);
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);

        Cart savedCart = cartRepository.findById(cart.getId()).orElse(null);

        assertNotNull(savedCart);
        assertEquals(1, savedCart.getCartItems().size());
        assertEquals(cartItem.getId(), savedCart.getCartItems().get(0).getId());
    }
}
