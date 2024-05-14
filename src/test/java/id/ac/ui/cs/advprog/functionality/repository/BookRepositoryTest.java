package id.ac.ui.cs.advprog.functionality.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import id.ac.ui.cs.advprog.functionality.enums.BookSortCriteria;
import id.ac.ui.cs.advprog.functionality.model.Book;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@DataJpaTest
@EnableJpaRepositories(basePackageClasses = BookRepository.class)
public class BookRepositoryTest {

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testFindByKeyword() {
        // Mock data
        Book book1 = new Book(1, "Title 1", "Author 1", "Publisher 1", 10.0, "Description 1", 10,
            "2024-01-01", "ISBN 1", 100, "Cover Picture 1", "Category 1", 5);
        Book book2 = new Book(2, "Title 2", "Author 2", "Publisher 2", 20.0, "Description 2", 20,
            "2024-02-02", "ISBN 2", 200, "Cover Picture 2", "Category 2", 10);

        when(bookRepository.findByKeyword("keyword")).thenReturn(Arrays.asList(book1, book2));

        // Test
        List<Book> books = bookRepository.findByKeyword("keyword");
        assertEquals(2, books.size());
        assertEquals(book1, books.get(0));
        assertEquals(book2, books.get(1));
    }

    @Test
    public void testSearchAndSortBooks() {
        // Mock data
        Book book1 = new Book(1, "Title 1", "Author 1", "Publisher 1", 10.0, "Description 1", 10,
            "2024-01-01", "ISBN 1", 100, "Cover Picture 1", "Category 1", 5);
        Book book2 = new Book(2, "Title 2", "Author 2", "Publisher 2", 20.0, "Description 2", 20,
            "2024-02-02", "ISBN 2", 200, "Cover Picture 2", "Category 2", 10);

        when(bookRepository.searchAndSortBooks("keyword", BookSortCriteria.NEWEST.getSortKey())).thenReturn(Arrays.asList(book2, book1));

        // Test
        List<Book> books = bookRepository.searchAndSortBooks("keyword", BookSortCriteria.NEWEST.getSortKey());
        assertEquals(2, books.size());
        assertEquals(book2, books.get(0)); // Newest first
        assertEquals(book1, books.get(1));
    }
}
