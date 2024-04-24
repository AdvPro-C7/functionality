package id.ac.ui.cs.advprog.functionality.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book(1, "Sample Title", "Sample Author", "Sample Publisher", 10.0, "Sample Description", 100, "2023-04-24", "1234567890", 300, "url/to/cover/picture", "Fiction", 50);
    }

    @Test
    public void testBookConstructorAndGetters() {
        assertNotNull(book, "Book instance should be created");
        assertEquals(1, book.getId(), "Check ID");
        assertEquals("Sample Title", book.getTitle(), "Check title");
        assertEquals("Sample Author", book.getAuthor(), "Check author");
        assertEquals("Sample Publisher", book.getPublisher(), "Check publisher");
        assertEquals(10.0, book.getPrice(), "Check price");
        assertEquals("Sample Description", book.getDescription(), "Check description");
        assertEquals(100, book.getStock(), "Check stock");
        assertEquals("2023-04-24", book.getPublishDate(), "Check publish date");
        assertEquals("1234567890", book.getIsbn(), "Check ISBN");
        assertEquals(300, book.getPages(), "Check pages");
        assertEquals("url/to/cover/picture", book.getCoverPicture(), "Check cover picture");
        assertEquals("Fiction", book.getCategory(), "Check category");
        assertEquals(50, book.getSold(), "Check sold count");
    }

    @Test
    public void testBookEmptyConstructor() {
        Book emptyBook = new Book();
        assertNotNull(emptyBook, "Empty constructor should create a non-null Book object");
    }
}

