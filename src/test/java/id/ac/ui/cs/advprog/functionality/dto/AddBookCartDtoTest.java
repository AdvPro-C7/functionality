package id.ac.ui.cs.advprog.functionality.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBookCartDtoTest {

    @Test
    public void testAddBookCartDto() {

        Long userId = 1L;
        Long bookId = 10L;

        AddBookCartDto addBookCartDto = new AddBookCartDto();
        addBookCartDto.setUserId(userId);
        addBookCartDto.setBookId(bookId);

        assertEquals(userId, addBookCartDto.getUserId());
        assertEquals(bookId, addBookCartDto.getBookId());
    }
}
