package id.ac.ui.cs.advprog.functionality.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AddBookCartDtoTest {

    private AddBookCartDto dto;

    @BeforeEach
    public void setUp() {
        dto = new AddBookCartDto();
    }

    @Test
    public void testAddBookCartDto() {
        Long userId = 1L;
        int bookId = 10;

        dto.setUserId(userId);
        dto.setBookId(bookId);

        assertEquals(userId, dto.getUserId());
        assertEquals(bookId, dto.getBookId());
    }

    @Test
    public void testGetterSetter() {
        dto.setUserId(1L);
        dto.setBookId(100);

        assertThat(dto.getUserId()).isEqualTo(1L);
        assertThat(dto.getBookId()).isEqualTo(100);
    }

    @Test
    public void testToString() {
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
    public void testEqualsSameObject() {
        dto.setUserId(1L);
        dto.setBookId(100);

        assertThat(dto).isEqualTo(dto);
    }

    @Test
    public void testNotEqualsNull() {
        dto.setUserId(1L);
        dto.setBookId(100);

        assertThat(dto).isNotEqualTo(null);
    }

    @Test
    public void testNotEqualsDifferentClass() {
        dto.setUserId(1L);
        dto.setBookId(100);

        String otherClassObject = "someString";

        assertThat(dto).isNotEqualTo(otherClassObject);
    }

    @Test
    public void testHashCodeConsistency() {
        dto.setUserId(1L);
        dto.setBookId(100);

        int initialHashCode = dto.hashCode();

        assertThat(dto.hashCode()).isEqualTo(initialHashCode);
    }

    @Test
    public void testSetUserIdToNull() {
        dto.setUserId(null);
        dto.setBookId(100);

        assertNull(dto.getUserId());
        assertEquals(100, dto.getBookId());
    }

    @Test
    public void testToStringWithNullValues() {
        dto.setUserId(null);
        dto.setBookId(100);

        String toString = dto.toString();
        assertThat(toString).contains("userId=null");
        assertThat(toString).contains("bookId=100");
    }

    @Test
    public void testHashCodeAfterStateChange() {
        dto.setUserId(1L);
        dto.setBookId(100);

        int initialHashCode = dto.hashCode();

        dto.setUserId(2L);
        int updatedHashCode = dto.hashCode();

        assertNotEquals(initialHashCode, updatedHashCode);
    }

    @Test
    public void testEqualsWithSameValues() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(1L);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(1L);
        dto2.setBookId(100);

        assertThat(dto1).isEqualTo(dto2);
    }

    @Test
    public void testEqualsWithDifferentInstances() {
        dto.setUserId(1L);
        dto.setBookId(100);

        assertThat(dto).isNotEqualTo(new Object());
    }

    @Test
    public void testEqualsWithNullValues() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(null);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(null);
        dto2.setBookId(100);

        assertThat(dto1).isEqualTo(dto2);
    }

    @Test
    public void testNotEqualsWithNullUserId() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(null);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(1L);
        dto2.setBookId(100);

        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    public void testNegativeBookId() {
        dto.setUserId(1L);
        dto.setBookId(-1);

        assertEquals(1L, dto.getUserId());
        assertEquals(-1, dto.getBookId());
    }

    @Test
    public void testZeroBookId() {
        dto.setUserId(1L);
        dto.setBookId(0);

        assertEquals(1L, dto.getUserId());
        assertEquals(0, dto.getBookId());
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
    public void testEqualsSameUserIdDifferentBookId() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(1L);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(1L);
        dto2.setBookId(101);

        assertNotEquals(dto1, dto2);
    }

    @Test
    public void testEqualsSameBookIdDifferentUserId() {
        AddBookCartDto dto1 = new AddBookCartDto();
        dto1.setUserId(1L);
        dto1.setBookId(100);

        AddBookCartDto dto2 = new AddBookCartDto();
        dto2.setUserId(2L);
        dto2.setBookId(100);

        assertNotEquals(dto1, dto2);
    }

    @Test
    public void testEqualsWithDefaultValues() {
        AddBookCartDto dto1 = new AddBookCartDto();
        AddBookCartDto dto2 = new AddBookCartDto();

        assertThat(dto1).isEqualTo(dto2);
    }
}
