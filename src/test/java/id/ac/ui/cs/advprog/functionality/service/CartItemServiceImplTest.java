package id.ac.ui.cs.advprog.functionality.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.CartItem;
import id.ac.ui.cs.advprog.functionality.repository.CartItemRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceImplTest {
    
    @InjectMocks
    private CartItemServiceImpl cartItemService;

    @InjectMocks
    private CartItemRepository cartItemRepository;

    @Mock
    private CartItem cartItem;

    @Mock
    private Book book;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testAddCartItem() {
        book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(20000);
        assertNotNull(cartItem);
        assertEquals(book, cartItemService.addCartItem(book, 1).getBook());
    }

    @Test
    public void testRemoveCartItem() {
        cartItem = new CartItem(book, 1);
        
    }
}
