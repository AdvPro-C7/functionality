package id.ac.ui.cs.advprog.functionality.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartItemsDtoTest {

    @Test
    public void testCartItemsDto() {
        // Create sample data
        Long id = 1L;
        Long price = 100L;
        Long quantity = 2L;
        Long bookId = 10L;
        Long orderId = 20L;
        String bookName = "Sample Book";
        String returnedImg = "sample_image.jpg";
        Long userId = 30L;

        // Create a CartItemsDto object
        CartItemsDto cartItemsDto = new CartItemsDto();
        cartItemsDto.setId(id);
        cartItemsDto.setPrice(price);
        cartItemsDto.setQuantity(quantity);
        cartItemsDto.setBookId(bookId);
        cartItemsDto.setOrderId(orderId);
        cartItemsDto.setBookName(bookName);
        cartItemsDto.setReturnedImg(returnedImg);
        cartItemsDto.setUserId(userId);

        // Test getters
        assertEquals(id, cartItemsDto.getId());
        assertEquals(price, cartItemsDto.getPrice());
        assertEquals(quantity, cartItemsDto.getQuantity());
        assertEquals(bookId, cartItemsDto.getBookId());
        assertEquals(orderId, cartItemsDto.getOrderId());
        assertEquals(bookName, cartItemsDto.getBookName());
        assertEquals(returnedImg, cartItemsDto.getReturnedImg());
        assertEquals(userId, cartItemsDto.getUserId());

        // Test setters
        Long newId = 2L;
        Long newPrice = 200L;
        Long newQuantity = 3L;
        Long newBookId = 20L;
        Long newOrderId = 30L;
        String newBookName = "New Sample Book";
        String newReturnedImg = "new_sample_image.jpg";
        Long newUserId = 40L;

        cartItemsDto.setId(newId);
        cartItemsDto.setPrice(newPrice);
        cartItemsDto.setQuantity(newQuantity);
        cartItemsDto.setBookId(newBookId);
        cartItemsDto.setOrderId(newOrderId);
        cartItemsDto.setBookName(newBookName);
        cartItemsDto.setReturnedImg(newReturnedImg);
        cartItemsDto.setUserId(newUserId);

        // Verify that setters worked correctly
        assertEquals(newId, cartItemsDto.getId());
        assertEquals(newPrice, cartItemsDto.getPrice());
        assertEquals(newQuantity, cartItemsDto.getQuantity());
        assertEquals(newBookId, cartItemsDto.getBookId());
        assertEquals(newOrderId, cartItemsDto.getOrderId());
        assertEquals(newBookName, cartItemsDto.getBookName());
        assertEquals(newReturnedImg, cartItemsDto.getReturnedImg());
        assertEquals(newUserId, cartItemsDto.getUserId());
    }
}
