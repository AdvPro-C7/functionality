package id.ac.ui.cs.advprog.functionality.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AddBookCartDtoTest {

    @Test
    public void testEqualsAndHashCode() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(1L);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(1L);
        dto2.setBookId(100);

        AddBookCartDto dto3 = new AddBookCartDto();
        dto3.setUserId(2L);
        dto3.setBookId(200);

        // Test equals
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);

        // Test hashCode
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    public void testToString() {
        AddBookCartDto dto = new AddBookCartDto();
        dto.setUserId(1L);
        dto.setBookId(100);

        String toString = dto.toString();
        assertThat(toString).contains("userId=1");
        assertThat(toString).contains("bookId=100");
    }

    @Test
    public void testGetterSetter() {
        AddBookCartDto dto = new AddBookCartDto();
        dto.setUserId(1L);
        dto.setBookId(100);

        assertEquals(1L, dto.getUserId());
        assertEquals(100, dto.getBookId());
    }

    // Additional tests to cover more scenarios
    @Test
    public void testEqualsWithNullUserId() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(null);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(null);
        dto2.setBookId(100);

        assertEquals(dto1, dto2);
    }

    @Test
    public void testEqualsWithDifferentUserId() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(1L);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(2L);
        dto2.setBookId(100);

        assertNotEquals(dto1, dto2);
    }

    @Test
    public void testEqualsWithDifferentBookId() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(1L);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(1L);
        dto2.setBookId(101);

        assertNotEquals(dto1, dto2);
    }

    // Test cases for edge scenarios
    @Test
    public void testEqualsSameObject() {
        AddBookCartDto dto = new AddBookCartDto();
        dto.setUserId(1L);
        dto.setBookId(100);

        assertEquals(dto, dto);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        AddBookCartDto dto = new AddBookCartDto();
        dto.setUserId(1L);
        dto.setBookId(100);

        String otherClassObject = "someString";

        assertNotEquals(dto, otherClassObject);
    }
}
