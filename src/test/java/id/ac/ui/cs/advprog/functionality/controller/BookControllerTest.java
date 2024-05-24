package id.ac.ui.cs.advprog.functionality.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private MockMvc mockMvc;
    private List<Book> books;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        books = new ArrayList<>();
        books.add(new Book(1, "Test Book", "Test Author", "Test Publisher", 10.0, "Test Description", 100, "2023-01-01", "1234567890", 300, "url/to/cover", "Fiction", 20));
    }

    @Test
    public void testGetBookList() throws Exception {
        when(bookService.findAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/book-list"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(objectMapper.writeValueAsString(books)));

        verify(bookService).findAllBooks();
    }

    @Test
    public void testSearchBooks() throws Exception {
        when(bookService.searchBooks("Test")).thenReturn(books);

        mockMvc.perform(get("/api/book-list/search")
                        .param("keyword", "Test"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(objectMapper.writeValueAsString(books)));

        verify(bookService).searchBooks("Test");
    }

    @Test
    public void testSearchAndSortBooks() throws Exception {
        when(bookService.searchAndSortBooks("Test", BookSortCriteria.POPULARITY)).thenReturn(books);

        mockMvc.perform(get("/api/book-list/search-sort")
                        .param("keyword", "Test")
                        .param("sortBy", "popularity"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(objectMapper.writeValueAsString(books)));

        verify(bookService).searchAndSortBooks("Test", BookSortCriteria.POPULARITY);
    }

    @Test
    public void testSearchAndSortBooksInvalidCriteria() throws Exception {
        mockMvc.perform(get("/api/book-list/search-sort")
                        .param("keyword", "Test")
                        .param("sortBy", "invalid"))
               .andExpect(status().isBadRequest())
               .andExpect(content().string("Invalid sort criteria: invalid"));
    }

    @Test
    public void testGetBookDetails() throws Exception {
        Book book = books.get(0);
        when(bookService.getBookById(1)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/book-list/details/1"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(objectMapper.writeValueAsString(book)));

        verify(bookService).getBookById(1);
    }

    @Test
    public void testGetBookDetailsNotFound() throws Exception {
        when(bookService.getBookById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/book-list/details/1"))
               .andExpect(status().isNotFound())
               .andExpect(content().string("Book not found"));

        verify(bookService).getBookById(1);
    }
}
