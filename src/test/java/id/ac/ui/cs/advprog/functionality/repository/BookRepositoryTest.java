package id.ac.ui.cs.advprog.functionality.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.functionality.model.Book;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryTest {
    private BookRepository bookRepository;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        bookRepository = new BookRepository();
        book1 = new Book(1, "Java Fundamentals", "Author One", "Publisher One", 50.0, "A comprehensive Java guide.", 100, "2023-01-01", "1234567890123", 500, "url/to/cover1.jpg", "Programming", 150);
        book2 = new Book(2, "Advanced Java", "Author Two", "Publisher Two", 60.0, "In-depth Java programming concepts.", 150, "2023-02-01", "1234567890124", 550, "url/to/cover2.jpg", "Programming", 200);
        book3 = new Book(3, "Beginning Java", "Author One", "Publisher One", 40.0, "Java for beginners.", 200, "2023-03-01", "1234567890125", 300, "url/to/cover3.jpg", "Programming", 100);
        bookRepository.getBooks().add(book1);
        bookRepository.getBooks().add(book2);
        bookRepository.getBooks().add(book3);
    }

    @Test
    public void testSearchBooksByTitle() {
        List<Book> result = bookRepository.searchBooks("Java");
        assertTrue(result.contains(book1) && result.contains(book2) && result.contains(book3));
        assertEquals(3, result.size());
    }

    @Test
    public void testSearchBooksByAuthor() {
        List<Book> result = bookRepository.searchBooks("Author One");
        assertTrue(result.contains(book1) && result.contains(book3));
        assertEquals(2, result.size());
    }

    @Test
    public void testSortBooksByNewest() {
        List<Book> result = bookRepository.sortBooksByNewest();
        assertEquals(book3, result.get(0));
        assertEquals(book2, result.get(1));
        assertEquals(book1, result.get(2));
    }

    @Test
    public void testSortBooksByPopularity() {
        List<Book> result = bookRepository.sortBooksByPopularity();
        assertEquals(book2, result.get(0));
        assertEquals(book1, result.get(1));
        assertEquals(book3, result.get(2));
    }

    @Test
    public void testSortBooksByPriceAscending() {
        List<Book> result = bookRepository.sortBooksByPriceAsc();
        assertEquals(book3, result.get(0));
        assertEquals(book1, result.get(1));
        assertEquals(book2, result.get(2));
    }

    @Test
    public void testSortBooksByPriceDescending() {
        List<Book> result = bookRepository.sortBooksByPriceDesc();
        assertEquals(book2, result.get(0));
        assertEquals(book1, result.get(1));
        assertEquals(book3, result.get(2));
    }

    @Test
    public void testSearchAndSortBooksByTitleAndPopularity() {
        List<Book> result = bookRepository.searchAndSortBooks("Java", "popularity");
        assertEquals(3, result.size());
        assertEquals(book2, result.get(0)); // Most popular
        assertEquals(book1, result.get(1));
        assertEquals(book3, result.get(2)); // Least popular
    }
}
