package id.ac.ui.cs.advprog.functionality.model;

import id.ac.ui.cs.advprog.functionality.dto.CartItemsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartItemsTest {
    Book book;
    @BeforeEach
    public void setUp() {
        this.book = new Book(12L, "Steins Gate", "Taka Himeno", "Nitroplus", 50000L, "Tentang ilmuwan yang menciptakan mesin waktu", 10,
                "15-10-2009", "111-111-1111-11-1", 250, "https://m.media-amazon.com/images/M/MV5BMjUxMzE4ZDctODNjMS00MzIwLThjNDktODkwYjc5YWU0MDc0XkEyXkFqcGdeQXVyNjc3OTE4Nzk@._V1_FMjpg_UX1000_.jpg",
                "Science Fiction", 0);
    }


    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        Long price = 100L;
        Long quantity = 2L;

        User user = new User();
        user.setId(20L);

        // Create a CartItems object
        CartItems cartItems = new CartItems();
        cartItems.setId(id);
        cartItems.setPrice(price);
        cartItems.setQuantity(quantity);
        cartItems.setBook(book);
        cartItems.setUser(user);

        assertEquals(id, cartItems.getId());
        assertEquals(price, cartItems.getPrice());
        assertEquals(quantity, cartItems.getQuantity());
        assertEquals(book, cartItems.getBook());
        assertEquals(user, cartItems.getUser());
    }

    @Test
    public void testGetCartDto() {
        Long id = 1L;
        Long price = 100L;
        Long quantity = 2L;

        User user = new User();
        user.setId(20L);

        // Create a CartItems object
        CartItems cartItems = new CartItems();
        cartItems.setId(id);
        cartItems.setPrice(price);
        cartItems.setQuantity(quantity);
        cartItems.setBook(book);
        cartItems.setUser(user);

        CartItemsDto cartItemsDto = cartItems.getCartDto();
        assertEquals(id, cartItemsDto.getId());
        assertEquals(price, cartItemsDto.getPrice());
        assertEquals(quantity, cartItemsDto.getQuantity());
        assertEquals(user.getId(), cartItemsDto.getUserId());
        assertEquals(book.getTitle(), cartItemsDto.getBookName());
        assertEquals(book.getCoverPicture(), cartItemsDto.getReturnedImg());
    }

    @Test
    public void testOrder() {
        Order order = new Order();
        order.setId(1L);
        CartItems cartItems = new CartItems();
        cartItems.setOrder(order);

        assertEquals(order, cartItems.getOrder());
    }

    @Test
    public void testNullBook() {
        CartItems cartItems = new CartItems();
        cartItems.setBook(null);

        assertThrows(NullPointerException.class, cartItems::getCartDto);
    }

    @Test
    public void testNullUser() {
        CartItems cartItems = new CartItems();
        cartItems.setUser(null);

        assertThrows(NullPointerException.class, cartItems::getCartDto);
    }

    @Test
    public void testNullOrder() {
        CartItems cartItems = new CartItems();
        cartItems.setOrder(null);

        assertThrows(NullPointerException.class, cartItems::getCartDto);
    }
}
