package id.ac.ui.cs.advprog.functionality.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBookCartDtoTest {

    @Test
    public void testAddBookCartDto() {
        Long userId = 1L;
        int bookId = 10;

        AddBookCartDto addBookCartDto = new AddBookCartDto();
        addBookCartDto.setUserId(userId);
        addBookCartDto.setBookId(bookId);

        assertEquals(userId, addBookCartDto.getUserId());
        assertEquals(bookId, addBookCartDto.getBookId());
    }
    @Test
    public void testGetterSetter() {
        AddBookCartDto dto = new AddBookCartDto();
        dto.setUserId(1L);
        dto.setBookId(100);

        assertThat(dto.getUserId()).isEqualTo(1L);
        assertThat(dto.getBookId()).isEqualTo(100);
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

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1).isNotEqualTo(dto3);

        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
        assertThat(dto1.hashCode()).isNotEqualTo(dto3.hashCode());
    }
    @Test
    public void testNotEqualsDifferentUserId() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(1L);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(2L);
        dto2.setBookId(100);

        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    public void testNotEqualsDifferentBookId() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(1L);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(1L);
        dto2.setBookId(101);

        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    public void testEqualsSameObject() {
        AddBookCartDto dto = new AddBookCartDto();
        dto.setUserId(1L);
        dto.setBookId(100);

        assertThat(dto).isEqualTo(dto);
    }

    @Test
    public void testNotEqualsNull() {
        AddBookCartDto dto = new AddBookCartDto();
        dto.setUserId(1L);
        dto.setBookId(100);

        assertThat(dto).isNotEqualTo(null);
    }

    @Test
    public void testNotEqualsDifferentClass() {
        AddBookCartDto dto = new AddBookCartDto();
        dto.setUserId(1L);
        dto.setBookId(100);

        String otherClassObject = "someString";

        assertThat(dto).isNotEqualTo(otherClassObject);
    }

    @Test
    public void testHashCodeConsistency() {
        AddBookCartDto dto = new AddBookCartDto();
        dto.setUserId(1L);
        dto.setBookId(100);

        int initialHashCode = dto.hashCode();

        assertThat(dto.hashCode()).isEqualTo(initialHashCode);
    }
}
