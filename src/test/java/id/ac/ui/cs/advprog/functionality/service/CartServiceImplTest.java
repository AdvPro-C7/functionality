package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.dto.AddBookCartDto;
import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.CartItems;
import id.ac.ui.cs.advprog.functionality.model.Order;
import id.ac.ui.cs.advprog.functionality.model.User;
import id.ac.ui.cs.advprog.functionality.repository.BookRepository;
import id.ac.ui.cs.advprog.functionality.repository.CartItemRepository;
import id.ac.ui.cs.advprog.functionality.repository.OrderRepository;
import id.ac.ui.cs.advprog.functionality.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    Book book;
    @BeforeEach
    void setUp() {
        this.book = new Book(12, "Steins Gate", "Taka Himeno", "Nitroplus", 50000L, "Tentang ilmuwan yang menciptakan mesin waktu", 10,
                "15-10-2009", "111-111-1111-11-1", 250, "https://m.media-amazon.com/images/M/MV5BMjUxMzE4ZDctODNjMS00MzIwLThjNDktODkwYjc5YWU0MDc0XkEyXkFqcGdeQXVyNjc3OTE4Nzk@._V1_FMjpg_UX1000_.jpg",
                "Science Fiction", 0);
    }

    @Test
    public void testAddBookToCart() {
        AddBookCartDto addBookCartDto = new AddBookCartDto();
        addBookCartDto.setBookId(1);
        addBookCartDto.setUserId(1L);

        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(0L);
        order.setCartItems(new ArrayList<>());

        when(orderRepository.findByUserIdAndStatus(addBookCartDto.getUserId(), OrderStatus.PENDING)).thenReturn(order);
        when(cartItemRepository.findByBookIdAndOrderIdAndUserId(addBookCartDto.getBookId(), order.getId(), addBookCartDto.getUserId())).thenReturn(Optional.empty());


        User user = new User();
        user.setId(1L);

        when(bookRepository.findById(addBookCartDto.getBookId())).thenReturn(Optional.of(book));
        when(userRepository.findById(addBookCartDto.getUserId())).thenReturn(Optional.of(user));

        ResponseEntity<?> response = cartService.addBookToCart(addBookCartDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(cartItemRepository, times(1)).save(any(CartItems.class));
    }
    @Test
    public void testAddBookToCartConflict() {
        AddBookCartDto addBookCartDto = new AddBookCartDto();
        addBookCartDto.setBookId(1);
        addBookCartDto.setUserId(1L);

        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(0.0);
        order.setCartItems(new ArrayList<>());

        User user = new User();
        user.setId(1L);

        CartItems existingCartItem = new CartItems();
        existingCartItem.setId(1L);
        existingCartItem.setBook(book);
        existingCartItem.setOrder(order);
        existingCartItem.setUser(user);

        when(orderRepository.findByUserIdAndStatus(addBookCartDto.getUserId(), OrderStatus.PENDING)).thenReturn(order);
        when(cartItemRepository.findByBookIdAndOrderIdAndUserId(addBookCartDto.getBookId(), order.getId(), addBookCartDto.getUserId())).thenReturn(Optional.of(existingCartItem));

        ResponseEntity<?> response = cartService.addBookToCart(addBookCartDto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Book is already in the cart", response.getBody());
        verify(cartItemRepository, never()).save(any(CartItems.class));
    }

}