package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.dto.AddBookCartDto;
import id.ac.ui.cs.advprog.functionality.dto.BookDto;
import id.ac.ui.cs.advprog.functionality.dto.PaymentDto;
import id.ac.ui.cs.advprog.functionality.dto.PlaceOrderDto;
import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import id.ac.ui.cs.advprog.functionality.model.CartItems;
import id.ac.ui.cs.advprog.functionality.model.Order;
import id.ac.ui.cs.advprog.functionality.repository.CartItemRepository;
import id.ac.ui.cs.advprog.functionality.repository.OrderRepository;
import jakarta.persistence.criteria.CriteriaBuilder.In;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CartServiceImplTest {

        @Mock
        private OrderRepository orderRepository;

        @Mock
        private CartItemRepository cartItemRepository;

        @Mock
        private RestTemplate restTemplate;

        @InjectMocks
        private CartServiceImpl cartService;

        @BeforeEach
        void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        void testAddBookToCart() {
                BookDto mockBook = new BookDto();
                mockBook.setId(1);
                mockBook.setTitle("Test Book");
                mockBook.setAuthor("Test Author");
                mockBook.setPrice(10.0);
                mockBook.setCoverPicture("test.jpg");

                ResponseEntity<BookDto> mockResponse = ResponseEntity.ok(mockBook);
                when(restTemplate.getForEntity(anyString(), eq(BookDto.class))).thenReturn(mockResponse);

                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                when(cartItemRepository.findByBookIdAndOrderIdAndUserId(anyInt(), anyLong(), anyLong()))
                                .thenReturn(Optional.empty());

                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                ResponseEntity<?> response = cartService.addBookToCart(addBookCartDto);

                assertEquals(HttpStatus.CREATED, response.getStatusCode());
                assertNotNull(response.getBody());
                verify(cartItemRepository, times(1)).save(any(CartItems.class));
                verify(orderRepository, times(1)).save(any(Order.class));
        }

        @Test
        void testAddBookExistCart() {
                BookDto mockBook = new BookDto();
                mockBook.setId(1);
                mockBook.setTitle("Test Book");
                mockBook.setAuthor("Test Author");
                mockBook.setPrice(10.0);
                mockBook.setCoverPicture("test.jpg");

                ResponseEntity<BookDto> mockResponse = ResponseEntity.ok(mockBook);
                when(restTemplate.getForEntity(anyString(), eq(BookDto.class))).thenReturn(mockResponse);

                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                CartItems mockCartItem = new CartItems();
                mockCartItem.setBookId(1);
                mockCartItem.setUserId(1L);
                when(cartItemRepository.findByBookIdAndOrderIdAndUserId(anyInt(), anyLong(), anyLong()))
                                .thenReturn(Optional.of(mockCartItem));

                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                ResponseEntity<?> response = cartService.addBookToCart(addBookCartDto);

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals("Book quantity updated in the cart", response.getBody());
                verify(cartItemRepository, times(1)).save(any(CartItems.class));
                verify(orderRepository, times(1)).save(any(Order.class));
        }

        @Test
        void testAddBookToCart_BookNotFound() {
                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                when(restTemplate.getForEntity(anyString(), eq(BookDto.class)))
                                .thenReturn(ResponseEntity.notFound().build());

                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                ResponseEntity<?> response = cartService.addBookToCart(addBookCartDto);

                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                verify(cartItemRepository, never()).save(any());
                verify(orderRepository, never()).save(any());
        }

        @Test
        void testAddBookToCart_BookNull() {
                ResponseEntity<BookDto> mockResponse = ResponseEntity.ok(null);
                when(restTemplate.getForEntity(anyString(), eq(BookDto.class))).thenReturn(mockResponse);

                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                ResponseEntity<?> response = cartService.addBookToCart(addBookCartDto);

                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
                assertEquals("Book is missing contact admin", response.getBody());
                verify(cartItemRepository, never()).save(any());
                verify(orderRepository, never()).save(any());
        }

        @Test
        void testGetBookDetails_RuntimeException() {
                int bookId = 123;
                when(restTemplate.getForEntity(ArgumentMatchers.any(String.class), eq(BookDto.class)))
                                .thenThrow(new RuntimeException("Simulated runtime exception"));

                CompletableFuture<ResponseEntity<BookDto>> future = cartService.getBookDetails(bookId);
                ResponseEntity<BookDto> response = future.join();
                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        }

        @Test
        void testCreateCart_noActiveOrder() {
                Long userId = 123L;
                ResponseEntity<?> responseEntity = cartService.createCart(userId);

                assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
                Order savedOrder = (Order) responseEntity.getBody();
                assertEquals(userId, savedOrder.getUserId());
                assertEquals(OrderStatus.PENDING, savedOrder.getStatus());
                assertEquals(0.0, savedOrder.getTotalPrice());
                assertEquals(new ArrayList<>(), savedOrder.getCartItems());

                verify(orderRepository, times(1)).findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING));
                verify(orderRepository, times(1)).save(any(Order.class));
                verifyNoMoreInteractions(orderRepository);
        }

        @Test
        void testCreateCart_activeOrderExists() {
                Order existingOrder = new Order();
                existingOrder.setId(1L);
                existingOrder.setStatus(OrderStatus.PENDING);
                existingOrder.setUserId(123L);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING)))
                                .thenReturn(existingOrder);

                Long userId = 123L;
                ResponseEntity<?> responseEntity = cartService.createCart(userId);

                assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
                assertEquals("Cart with that user id already created", responseEntity.getBody());

                verify(orderRepository, times(1)).findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING));
                verifyNoMoreInteractions(orderRepository);
        }

        @Test
        void testGetCartByUserId_activeOrderFound() {

                Long userId = 123L;
                Order activeOrder = new Order();
                activeOrder.setId(1L);
                activeOrder.setUserId(userId);
                activeOrder.setStatus(OrderStatus.PENDING);

                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING)))
                                .thenReturn(activeOrder);

                ResponseEntity<?> responseEntity = cartService.getCartByUserId(userId);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals(activeOrder, responseEntity.getBody());
        }

        @Test
        void testGetCartByUserId_noActiveOrderFound() {
                Long userId = 456L;

                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING)))
                                .thenReturn(null);

                ResponseEntity<?> responseEntity = cartService.getCartByUserId(userId);

                assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
                assertEquals(null, responseEntity.getBody());
        }

        @Test
        public void testIncreaseProductQuantity_itemExists_and_bookDetailsFound() {
                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                Order userOrder = new Order();
                userOrder.setId(1L);
                userOrder.setUserId(addBookCartDto.getUserId());
                userOrder.setStatus(OrderStatus.PENDING);
                userOrder.setTotalPrice(100.0);

                CartItems cartItem = new CartItems();
                cartItem.setUserId(addBookCartDto.getUserId());
                cartItem.setBookId(addBookCartDto.getBookId());
                cartItem.setQuantity(2);

                BookDto bookDto = new BookDto();
                bookDto.setId(addBookCartDto.getBookId());
                bookDto.setTitle("Sample Book");
                bookDto.setPrice(25.0);

                ResponseEntity<BookDto> bookResponse = ResponseEntity.ok(bookDto);

                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING)))
                                .thenReturn(userOrder);
                when(cartItemRepository.findByBookIdAndOrderIdAndUserId(
                                eq(addBookCartDto.getBookId()), eq(userOrder.getId()), eq(addBookCartDto.getUserId())))
                                .thenReturn(Optional.of(cartItem));
                when(restTemplate.getForEntity(anyString(), eq(BookDto.class))).thenReturn(bookResponse);

                ResponseEntity<?> responseEntity = cartService.increaseProductQuantity(addBookCartDto);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                Order updatedOrder = (Order) responseEntity.getBody();
                assertEquals(userOrder.getId(), updatedOrder.getId());
                assertEquals(userOrder.getUserId(), updatedOrder.getUserId());
                assertEquals(125.0, updatedOrder.getTotalPrice()); // Expected total price after increase
        }

        @Test
        void testIncreaseToCart_BookNotFound() {
                CartItems mockCartItem = new CartItems();
                mockCartItem.setBookId(1);
                mockCartItem.setUserId(1L);
                when(cartItemRepository.findByBookIdAndOrderIdAndUserId(anyInt(), anyLong(), anyLong()))
                                .thenReturn(Optional.of(mockCartItem));

                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                when(restTemplate.getForEntity(anyString(), eq(BookDto.class)))
                                .thenReturn(ResponseEntity.notFound().build());

                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                ResponseEntity<?> response = cartService.increaseProductQuantity(addBookCartDto);

                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
                verify(cartItemRepository, never()).save(any());
                verify(orderRepository, never()).save(any());
        }

        @Test
        void testIncreaseToCart_BookNull() {
                CartItems mockCartItem = new CartItems();
                mockCartItem.setBookId(1);
                mockCartItem.setUserId(1L);
                when(cartItemRepository.findByBookIdAndOrderIdAndUserId(anyInt(), anyLong(), anyLong()))
                                .thenReturn(Optional.of(mockCartItem));

                ResponseEntity<BookDto> mockResponse = ResponseEntity.ok(null);
                when(restTemplate.getForEntity(anyString(), eq(BookDto.class))).thenReturn(mockResponse);

                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                ResponseEntity<?> response = cartService.increaseProductQuantity(addBookCartDto);

                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
                assertEquals("Book is missing contact admin", response.getBody());
                verify(cartItemRepository, never()).save(any());
                verify(orderRepository, never()).save(any());
        }

        @Test
        void testIncreaseToCart_CartItemNotFound() {
                CartItems mockCartItem = new CartItems();
                mockCartItem.setBookId(1);
                mockCartItem.setUserId(1L);
                when(cartItemRepository.findByBookIdAndOrderIdAndUserId(anyInt(), anyLong(), anyLong()))
                                .thenReturn(Optional.empty());

                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                ResponseEntity<?> response = cartService.increaseProductQuantity(addBookCartDto);

                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                verify(cartItemRepository, never()).save(any());
                verify(orderRepository, never()).save(any());
        }

        @Test
        public void testDecreaseProductQuantity_itemExists_and_bookDetailsFound() {
                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                Order userOrder = new Order();
                userOrder.setId(1L);
                userOrder.setUserId(addBookCartDto.getUserId());
                userOrder.setStatus(OrderStatus.PENDING);
                userOrder.setTotalPrice(100.0);

                CartItems cartItem = new CartItems();
                cartItem.setUserId(addBookCartDto.getUserId());
                cartItem.setBookId(addBookCartDto.getBookId());
                cartItem.setQuantity(2);

                BookDto bookDto = new BookDto();
                bookDto.setId(addBookCartDto.getBookId());
                bookDto.setTitle("Sample Book");
                bookDto.setPrice(25.0);

                ResponseEntity<BookDto> bookResponse = ResponseEntity.ok(bookDto);

                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING)))
                                .thenReturn(userOrder);
                when(cartItemRepository.findByBookIdAndOrderIdAndUserId(
                                eq(addBookCartDto.getBookId()), eq(userOrder.getId()), eq(addBookCartDto.getUserId())))
                                .thenReturn(Optional.of(cartItem));
                when(restTemplate.getForEntity(anyString(), eq(BookDto.class))).thenReturn(bookResponse);

                ResponseEntity<?> responseEntity = cartService.decreaseProductQuantity(addBookCartDto);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                Order updatedOrder = (Order) responseEntity.getBody();
                assertEquals(userOrder.getId(), updatedOrder.getId());
                assertEquals(userOrder.getUserId(), updatedOrder.getUserId());
                assertEquals(75.0, updatedOrder.getTotalPrice()); // Expected total price after increase
        }

        @Test
        void testDecreaseToCart_BookNotFound() {
                CartItems mockCartItem = new CartItems();
                mockCartItem.setBookId(1);
                mockCartItem.setUserId(1L);
                when(cartItemRepository.findByBookIdAndOrderIdAndUserId(anyInt(), anyLong(), anyLong()))
                                .thenReturn(Optional.of(mockCartItem));

                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                when(restTemplate.getForEntity(anyString(), eq(BookDto.class)))
                                .thenReturn(ResponseEntity.notFound().build());

                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                ResponseEntity<?> response = cartService.decreaseProductQuantity(addBookCartDto);

                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
                verify(cartItemRepository, never()).save(any());
                verify(orderRepository, never()).save(any());
        }

        @Test
        void testDecreaseToCart_BookNull() {
                CartItems mockCartItem = new CartItems();
                mockCartItem.setBookId(1);
                mockCartItem.setUserId(1L);
                when(cartItemRepository.findByBookIdAndOrderIdAndUserId(anyInt(), anyLong(), anyLong()))
                                .thenReturn(Optional.of(mockCartItem));

                ResponseEntity<BookDto> mockResponse = ResponseEntity.ok(null);
                when(restTemplate.getForEntity(anyString(), eq(BookDto.class))).thenReturn(mockResponse);

                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                ResponseEntity<?> response = cartService.decreaseProductQuantity(addBookCartDto);

                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
                assertEquals("Book is missing contact admin", response.getBody());
                verify(cartItemRepository, never()).save(any());
                verify(orderRepository, never()).save(any());
        }

        @Test
        void testDecreaseToCart_CartItemNotFound() {
                CartItems mockCartItem = new CartItems();
                mockCartItem.setBookId(1);
                mockCartItem.setUserId(1L);
                when(cartItemRepository.findByBookIdAndOrderIdAndUserId(anyInt(), anyLong(), anyLong()))
                                .thenReturn(Optional.empty());

                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                AddBookCartDto addBookCartDto = new AddBookCartDto();
                addBookCartDto.setUserId(1L);
                addBookCartDto.setBookId(1);
                addBookCartDto.setQuantity(1);

                ResponseEntity<?> response = cartService.decreaseProductQuantity(addBookCartDto);

                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                verify(cartItemRepository, never()).save(any());
                verify(orderRepository, never()).save(any());
        }

        @Test
        public void testDeleteCartItem_cartItemExists() {
                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                mockOrder.setTotalPrice(100.0);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                Long cartItemId = 1L;
                CartItems cartItem = new CartItems();
                cartItem.setPrice(25.0);
                cartItem.setQuantity(2);
                cartItem.setOrder(mockOrder);

                when(cartItemRepository.findById(anyLong()))
                                .thenReturn(Optional.of(cartItem));

                ResponseEntity<?> responseEntity = cartService.deleteCartItem(cartItemId);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals("Cart item deleted successfully", responseEntity.getBody());

                verify(cartItemRepository, times(1)).findById(anyLong());
                verify(cartItemRepository, times(1)).delete(any(CartItems.class));
                verify(orderRepository, times(1)).save(any(Order.class));
        }

        @Test
        public void testDeleteCartItem_cartItemNotFound() {
                Order mockOrder = new Order();
                mockOrder.setId(1L);
                mockOrder.setUserId(1L);
                mockOrder.setStatus(OrderStatus.PENDING);
                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING))).thenReturn(mockOrder);

                Long cartItemId = 1L;

                when(cartItemRepository.findById(anyLong()))
                                .thenReturn(Optional.empty());

                ResponseEntity<?> responseEntity = cartService.deleteCartItem(cartItemId);

                assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
                assertEquals("Cart item not found", responseEntity.getBody());

                verify(cartItemRepository, times(1)).findById(anyLong());
                verifyNoMoreInteractions(cartItemRepository);
                verifyNoInteractions(orderRepository);
        }

}
