package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
        lenient().when(bookRepository.getBooks()).thenReturn(books);
    }

    @Test
    public void testFindAllBooks() {
        List<Book> result = bookService.findAllBooks();
        assertEquals(books, result);
        verify(bookRepository).getBooks();
    }

    @Test
    public void testSearchBooks() {
        when(bookRepository.searchBooks("Sample")).thenReturn(books);
        List<Book> result = bookService.searchBooks("Sample");
        assertEquals(books, result);
        verify(bookRepository).searchBooks("Sample");
    }

    @Test
    public void testFindBooksByNewest() {
        when(bookRepository.sortBooksByNewest()).thenReturn(books);
        List<Book> result = bookService.findBooksByNewest();
        assertEquals(books, result);
        verify(bookRepository).sortBooksByNewest();
    }

    @Test
    public void testFindBooksByPopularity() {
        when(bookRepository.sortBooksByPopularity()).thenReturn(books);
        List<Book> result = bookService.findBooksByPopularity();
        assertEquals(books, result);
        verify(bookRepository).sortBooksByPopularity();
    }

    @Test
    public void testFindBooksByPriceAsc() {
        when(bookRepository.sortBooksByPriceAsc()).thenReturn(books);
        List<Book> result = bookService.findBooksByPriceAsc();
        assertEquals(books, result);
        verify(bookRepository).sortBooksByPriceAsc();
    }

    @Test
    public void testFindBooksByPriceDesc() {
        when(bookRepository.sortBooksByPriceDesc()).thenReturn(books);
        List<Book> result = bookService.findBooksByPriceDesc();
        assertEquals(books, result);
        verify(bookRepository).sortBooksByPriceDesc();
    }

    @Test
    public void testSearchAndSortBooks() {
        when(bookRepository.searchAndSortBooks("Sample", "popularity")).thenReturn(books);
        List<Book> result = bookService.searchAndSortBooks("Sample", "popularity");
        assertEquals(books, result);
        verify(bookRepository).searchAndSortBooks("Sample", "popularity");
    }

    @Test
    public void testGetBookByIndex() {
        when(bookRepository.getBookByIdx(0)).thenReturn(books.get(0));
        Book result = bookService.getBookByIndex(0);
        assertEquals(books.get(0), result);
        verify(bookRepository).getBookByIdx(0);
    }

    @Test
    public void testIsRepositoryEmpty() {
        when(bookRepository.isEmpty()).thenReturn(true);
        assertTrue(bookService.isRepositoryEmpty());
        verify(bookRepository).isEmpty();
    }

    @Test
    public void testGetEmptyRepositoryMessage() {
        when(bookRepository.getEmptyMessage()).thenReturn("No books available");
        assertEquals("No books available", bookService.getEmptyRepositoryMessage());
        verify(bookRepository).getEmptyMessage();
    }
}
