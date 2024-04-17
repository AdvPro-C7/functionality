package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.Cart;
import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.CartItem;
import id.ac.ui.cs.advprog.functionality.model.User;
import id.ac.ui.cs.advprog.functionality.repository.CartRepository;
import id.ac.ui.cs.advprog.functionality.repository.CartItemRepository;
import id.ac.ui.cs.advprog.functionality.service.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBeans;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;


    @Mock
    private Cart cart;

    @Mock
    private CartItem cartItem;

    @Mock
    private Book book;

    @Mock
    private User user;
    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
    
        cart = new Cart(user);
        when(cartRepository.save(any(Cart.class))).then(AdditionalAnswers.returnsFirstArg());
        when(cartRepository.findById(any(Integer.class))).thenReturn(Optional.of(cart));

    }

    @Test
    public void testAddItemToCart() {
        book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(20000);
        book.setId(1);

        cartItem = new CartItem(book, 1);
        cartItem.setId(3);
        
        Cart updatedCart = cartService.addItemToCart(user.getId(), cartItem);
        
        assertEquals(1, updatedCart.getId());
        assertEquals(1, updatedCart.getCartItems().size());
    }

    @Test
    public void testAddMoreThanOneItemToCart(){
        book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(20000);
        book.setId(1);

        cartItem = new CartItem(book, 1);
        cartItem.setId(3);
        
        Cart updatedCart = cartService.addItemToCart(user.getId(), cartItem);
        
        assertEquals(1, updatedCart.getId());
        assertEquals(1, updatedCart.getCartItems().size());
        
        CartItem cartItem2 = new CartItem(book, 1);
        cartItem2.setId(4);
        
        updatedCart = cartService.addItemToCart(user.getId(), cartItem2);
        
        assertEquals(1, updatedCart.getId());
        assertEquals(2, updatedCart.getCartItems().size());
    }
    
    @Test
    public void testDeleteItemFromCart(){
        book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(20000);
        book.setId(1);

        user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
        
        cart = new Cart(user);
        
        cartItem = new CartItem(book, 1);
        cartItem.setId(3);
        
        cartRepository.save(cart);
        cart.addCartItem(cartItem);
        
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        
        boolean isDeleted = cartService.deleteItemFromCart(user.getId(), cartItem);
        
        assertTrue(isDeleted);
        assertEquals(0, cart.getCartItems().size());
    }

    @Test
    public void testDeleteAllItemsFromCart(){
        book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(20000);
        book.setId(1);

        user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
        
        cart = new Cart(user);
        
        cartItem = new CartItem(book, 1);
        cartItem.setId(3);
        cartRepository.save(cart);

        cartItem = new CartItem(book, 1);
        
        cart.addCartItem(cartItem);
        
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        
        boolean isDeleted = cartService.deleteAllItemsFromCart(user.getId());
        
        assertTrue(isDeleted);
        assertEquals(0, cart.getCartItems().size());
    }


}
