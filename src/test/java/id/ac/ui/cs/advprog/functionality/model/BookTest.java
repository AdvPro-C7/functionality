package id.ac.ui.cs.advprog.functionality.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BookTest {

    Book book1;

    @BeforeEach
    public void setUp(){

        this.book1 = new Book("abcd", "Steins Gate", "Taka Himeno", "Nitroplus", 50000, "Tentang ilmuwan yang menciptakan mesin waktu", 10,
                "15-10-2009", "111-111-1111-11-1", 250, "https://m.media-amazon.com/images/M/MV5BMjUxMzE4ZDctODNjMS00MzIwLThjNDktODkwYjc5YWU0MDc0XkEyXkFqcGdeQXVyNjc3OTE4Nzk@._V1_FMjpg_UX1000_.jpg",
                "Science Fiction", 0);
    }

    @Test
    void testGetDetailsBook(){
        assertEquals("abcd", book1.getId());
        assertEquals("Steins Gate", book1.getTitle());
        assertEquals("Taka Himeno", book1.getAuthor());
        assertEquals("Nitroplus", book1.getPublisher());
        assertEquals("Tentang ilmuwan yang menciptakan mesin waktu", book1.getDescription());
        assertEquals(50000, book1.getPrice());
        assertEquals(10, book1.getStock());
        assertEquals("15-10-2009", book1.getPublishDate());
        assertEquals("111-111-1111-11-1", book1.getIsbn());
        assertEquals(250, book1.getPages());
        assertEquals("https://m.media-amazon.com/images/M/MV5BMjUxMzE4ZDctODNjMS00MzIwLThjNDktODkwYjc5YWU0MDc0XkEyXkFqcGdeQXVyNjc3OTE4Nzk@._V1_FMjpg_UX1000_.jpg", book1.getCoverPicture());
        assertEquals("Science Fiction", book1.getCategory());
        assertEquals(0, book1.getSold());
    }

    @Test
    void testUpdateSoldValueWithNegativeNumber(){
        assertThrows(IllegalArgumentException.class, () -> {
            book1.setSold(-1);
        });
    }

    @Test
    void testUpdateSoldValueWithValidNumber(){
        book1.setSold(2);

        assertEquals(2, book1.getSold());
    }

    @Test
    void testUpdateStockValueWithNegativeNumber(){
        assertThrows(IllegalArgumentException.class, () -> {
            book1.setStock(-1);
        });
    }

    @Test
    void testUpdateStockValueWithZeroNumber(){
        assertThrows(IllegalArgumentException.class, () -> {
            book1.setStock(0);
        });
    }

    @Test
    void testUpdateStockValueWithValidNumber(){
        book1.setStock(2);

        assertEquals(2, book1.getStock());
    }



}
