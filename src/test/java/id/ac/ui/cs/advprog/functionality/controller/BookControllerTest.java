package id.ac.ui.cs.advprog.functionality.controller;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private Model model;

    private MockMvc mockMvc;
    private List<Book> books;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        books = new ArrayList<>();
        books.add(new Book(1, "Test Book", "Test Author", "Test Publisher", 10.0, "Test Description", 100, "2023-01-01", "1234567890", 300, "url/to/cover", "Fiction", 20));
    }

    @Test
    public void testGetBookListPage() throws Exception {
        when(bookService.findAllBooks()).thenReturn(books);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/book-list"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
               .andExpect(MockMvcResultMatchers.model().attribute("books", books))
               .andExpect(MockMvcResultMatchers.view().name("BookListPage"));

        verify(bookService).findAllBooks();
    }

    @Test
    public void testSearchBooks() throws Exception {
        when(bookService.searchBooks("Test")).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/book-list/search")
                                              .param("keyword", "Test"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
               .andExpect(MockMvcResultMatchers.model().attribute("books", books))
               .andExpect(MockMvcResultMatchers.view().name("BookListPage"));

        verify(bookService).searchBooks("Test");
    }

    @Test
    public void testSortBooks() throws Exception {
        when(bookService.findBooksByNewest()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/book-list/sort")
                                              .param("criteria", "newest"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
               .andExpect(MockMvcResultMatchers.model().attribute("books", books))
               .andExpect(MockMvcResultMatchers.view().name("BookListPage"));

        verify(bookService).findBooksByNewest();
    }

    @Test
    public void testSearchAndSortBooks() throws Exception {
        when(bookService.searchAndSortBooks("Test", "popularity")).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/book-list/search-sort")
                                              .param("keyword", "Test")
                                              .param("sortBy", "popularity"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
               .andExpect(MockMvcResultMatchers.model().attribute("books", books))
               .andExpect(MockMvcResultMatchers.view().name("BookListPage"));

        verify(bookService).searchAndSortBooks("Test", "popularity");
    }
}
