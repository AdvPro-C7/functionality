package id.ac.ui.cs.advprog.functionality.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookListTest {

    @Test
    public void testEmptyBookList() {
        BookList bookList = new BookList();
        assertTrue(bookList.isEmpty());
        assertEquals("No books available", bookList.getEmptyMessage());
        assertNull(bookList.getBookByIdx(0));
    }

    @Test
    public void testBookListWithBooks() {
        BookList bookList = new BookList();
        Book book1 = new Book("1", "Book 1", 2024);
        Book book2 = new Book("2", "Book 2", 2024);
        bookList.getBooks().add(book1);
        bookList.getBooks().add(book2);

        assertFalse(bookList.isEmpty());
        assertEquals(book1, bookList.getBookByIdx(0));
        assertEquals(book2, bookList.getBookByIdx(1));
        assertNull(bookList.getBookByIdx(2));
    }
}