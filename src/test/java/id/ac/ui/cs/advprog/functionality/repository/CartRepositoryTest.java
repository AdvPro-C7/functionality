package id.ac.ui.cs.advprog.functionality.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.Cart;
import id.ac.ui.cs.advprog.functionality.model.CartItem;
import id.ac.ui.cs.advprog.functionality.model.User;



@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    private Book book;
    private User user;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(20000);

        user = new User();
        user.setUsername("test");
        user.setPassword("test");
    }

    @Test
    public void testSaveCart() {
        Cart cart = new Cart(user);
        cartRepository.save(cart);
        Cart savedCart = cartRepository.findById(cart.getId()).orElse(null);

        assertNotNull(savedCart);
        assertEquals(cart.getUser(), savedCart.getUser());
    }

    @Test
    public void testAddCartItem() {
        Cart cart = new Cart(user);
        cartRepository.save(cart);

        CartItem cartItem = new CartItem(book, 1);
        cartItemRepository.save(cartItem);

        cart.addCartItem(cartItem);
        cartRepository.save(cart);

        Cart updatedCart = cartRepository.findById(cart.getId()).orElse(null);

        assertNotNull(updatedCart);
        assertEquals(1, updatedCart.getCartItems().size());
    }

    @Test
    public void testRemoveCartItem() {
        Cart cart = new Cart(user);
        cartRepository.save(cart);

        CartItem cartItem = new CartItem(book, 1);
        cartItemRepository.save(cartItem);

        cart.addCartItem(cartItem);
        cartRepository.save(cart);

        cart.removeCartItem(cartItem);
        cartRepository.save(cart);

        Cart updatedCart = cartRepository.findById(cart.getId()).orElse(null);

        assertNotNull(updatedCart);
        assertEquals(0, updatedCart.getCartItems().size());
    }

    @Test
    public void testAddMoreThanOne(){
        Cart cart = new Cart(user);
        cartRepository.save(cart);

        CartItem cartItem = new CartItem(book, 1);
        cartItemRepository.save(cartItem);

        cart.addCartItem(cartItem);
        cartRepository.save(cart);

        CartItem cartItem2 = new CartItem(book, 1);
        cartItemRepository.save(cartItem2);

        cart.addCartItem(cartItem2);
        cartRepository.save(cart);

        Cart updatedCart = cartRepository.findById(cart.getId()).orElse(null);

        assertNotNull(updatedCart);
        assertEquals(2, updatedCart.getCartItems().size());
    }

}
