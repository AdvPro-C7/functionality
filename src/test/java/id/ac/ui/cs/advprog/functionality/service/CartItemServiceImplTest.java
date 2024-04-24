package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.CartItem;
import id.ac.ui.cs.advprog.functionality.repository.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceImplTest {

    @InjectMocks
    private CartItemServiceImpl cartItemService;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private CartItem cartItem;

    @Mock
    private Book book;

    @BeforeEach
    public void setUp() {
        Book book = new Book();
        book.setTitle("title");
        book.setAuthor("author");
        book.setPrice(20000);

        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(1);

        when(cartItemRepository.save(any(CartItem.class))).then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    public void testCreateCartItem() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(20000);
        CartItem cartItem = new CartItem(book, 1);
        cartItem.setId(1);

        assertNotNull(cartItem);
        assertEquals(book, cartItemService.createCartItem(cartItem).getBook());
    }

    @Test
    public void testDeleteCartItem() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(20000);
        CartItem cartItem = new CartItem(book, 1);
        cartItem.setId(1);
        assertNotNull(cartItem);
        cartItemService.createCartItem(cartItem);
        when(cartItemRepository.findById(cartItem.getId())).thenReturn(Optional.of(cartItem));
        assertTrue(cartItemService.deleteCartItem(cartItem));

    }

}
