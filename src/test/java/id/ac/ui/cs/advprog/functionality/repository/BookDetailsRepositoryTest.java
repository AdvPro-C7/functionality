package id.ac.ui.cs.advprog.functionality.repository;

import id.ac.ui.cs.advprog.functionality.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = true)
public class BookDetailsRepositoryTest {

    @Autowired
    BookDetailsRepository bookDetailsRepository;

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
    void testFindByIdIfIdNotFound(){
        for (Book book : books){
            bookDetailsRepository.save(book);
        }

        Book findResult = bookDetailsRepository.findById(10).orElse(null);
        assertNull(findResult);
    }

    @Test
    void testGetAllBook(){
        for (Book book : books){
            bookDetailsRepository.save(book);
        }

        Iterable<Book> bookList = bookDetailsRepository.findAll();
        long bookCount = StreamSupport.stream(bookList.spliterator(), false).count();
        assertEquals(2, bookCount);
    }
}
