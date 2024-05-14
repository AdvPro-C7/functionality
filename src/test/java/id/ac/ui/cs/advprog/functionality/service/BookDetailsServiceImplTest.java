package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.repository.BookDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class BookDetailsServiceImplTest {

    @InjectMocks
    BookDetailsServiceImpl bookDetailsService;

    @Mock
    BookDetailsRepository bookDetailsRepository;

    @Mock
    Book book;

    List<Book> books;

    @BeforeEach
    public void setUp(){
        books = new ArrayList<>();

        Book book1 = new Book(1, "Steins Gate 0", "Taka Himeno", "Nitroplus", 100000,
                "Tentang ilmuwan yang ingin memperbaiki kesalahnnya akibat mesin waktu", 2, "10-12-2015", "111-111-1111-11-1",
                250, "https://m.media-amazon.com/images/M/MV5BMjUxMzE4ZDctODNjMS00MzIwLThjNDktODkwYjc5YWU0MDc0XkEyXkFqcGdeQXVyNjc3OTE4Nzk@._V1_FMjpg_UX1000_.jpg",
                "Science Fiction",0);

        books.add(book1);

        Book book2 = new Book(2, "Steins Gate", "Taka Himeno", "Nitroplus", 50000, "Tentang ilmuwan yang menciptakan mesin waktu",
                10, "15-10-2009", "111-111-1111-11-1", 250, "https://m.media-amazon.com/images/M/MV5BMjUxMzE4ZDctODNjMS00MzIwLThjNDktODkwYjc5YWU0MDc0XkEyXkFqcGdeQXVyNjc3OTE4Nzk@._V1_FMjpg_UX1000_.jpg",
                "Science Fiction", 0);

        books.add(book2);
    }

    @Test
    void testFindByIdIfFound(){
        Book book1 = books.getFirst();
        doReturn(Optional.of(book1)).when(bookDetailsRepository).findById(book1.getId());

        Optional<Book> result = bookDetailsService.findById(book1.getId());
        assertTrue(result.isPresent());
        assertEquals(book1.getId(), result.get().getId());
    }

    @Test
    void testFindByIdIfNotFound(){
        doReturn(Optional.empty()).when(bookDetailsRepository).findById(10);

        Optional<Book> result = bookDetailsService.findById(10);
        assertTrue(result.isEmpty());
    }

}
