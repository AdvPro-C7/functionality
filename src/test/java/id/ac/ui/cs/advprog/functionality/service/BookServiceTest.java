package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.enums.BookSortCriteria;
import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    List<Book> books;

    @BeforeEach
    public void setUp() {
        books = new ArrayList<>();
        books.add(new Book(1, "Sample Title", "Sample Author", "Sample Publisher", 10.0, "Sample Description", 100, "2023-04-24", "1234567890", 300, "url/to/cover/picture", "Fiction", 50));
    }

    @Test
    public void testFindAllBooks() {
        when(bookRepository.findAll()).thenReturn(books);
        List<Book> result = bookService.findAllBooks();
        assertEquals(books, result);
        verify(bookRepository).findAll();
    }

    @Test
    public void testSearchBooks() {
        when(bookRepository.findByKeyword("Sample")).thenReturn(books);
        List<Book> result = bookService.searchBooks("Sample");
        assertEquals(books, result);
        verify(bookRepository).findByKeyword("Sample");
    }

    @Test
    public void testFindBooksByNewest() {
        when(bookRepository.findAll(Sort.by(Sort.Direction.DESC, "publishDate"))).thenReturn(books);
        List<Book> result = bookService.findBooksByNewest();
        assertEquals(books, result);
        verify(bookRepository).findAll(Sort.by(Sort.Direction.DESC, "publishDate"));
    }

    @Test
    public void testFindBooksByPopularity() {
        when(bookRepository.findAll(Sort.by(Sort.Direction.DESC, "sold"))).thenReturn(books);
        List<Book> result = bookService.findBooksByPopularity();
        assertEquals(books, result);
        verify(bookRepository).findAll(Sort.by(Sort.Direction.DESC, "sold"));
    }

    @Test
    public void testFindBooksByPriceAsc() {
        when(bookRepository.findAll(Sort.by(Sort.Direction.ASC, "price"))).thenReturn(books);
        List<Book> result = bookService.findBooksByPriceAsc();
        assertEquals(books, result);
        verify(bookRepository).findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    @Test
    public void testFindBooksByPriceDesc() {
        when(bookRepository.findAll(Sort.by(Sort.Direction.DESC, "price"))).thenReturn(books);
        List<Book> result = bookService.findBooksByPriceDesc();
        assertEquals(books, result);
        verify(bookRepository).findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

    @Test
    public void testSearchAndSortBooks() {
        when(bookRepository.searchAndSortBooks("Sample", "popularity")).thenReturn(books);
        List<Book> result = bookService.searchAndSortBooks("Sample", BookSortCriteria.POPULARITY);
        assertEquals(books, result);
        verify(bookRepository).searchAndSortBooks("Sample", "popularity");
    }

    @Test
    public void testGetBookById() {
        Optional<Book> book = Optional.of(books.get(0));
        when(bookRepository.findById(1)).thenReturn(book);
        Optional<Book> result = bookService.getBookById(1);
        assertEquals(book, result);
        verify(bookRepository).findById(1);
    }

    @Test
    public void testIsRepositoryEmpty() {
        when(bookRepository.count()).thenReturn(0L);
        assertTrue(bookService.isRepositoryEmpty());
        verify(bookRepository).count();
    }
}
