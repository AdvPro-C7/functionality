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
    public void setUp() {
        books = new ArrayList<>();

        Book book1 = new Book(1, "Steins Gate 0", "Taka Himeno", "Nitroplus", 100000,
                "Tentang ilmuwan yang ingin memperbaiki kesalahnnya akibat mesin waktu", 2, "10-12-2015",
                "111-111-1111-11-1",
                250,
                "https://m.media-amazon.com/images/M/MV5BMjUxMzE4ZDctODNjMS00MzIwLThjNDktODkwYjc5YWU0MDc0XkEyXkFqcGdeQXVyNjc3OTE4Nzk@._V1_FMjpg_UX1000_.jpg",
                "Science Fiction", 50);

        books.add(book1);

        Book book2 = new Book(2, "Steins Gate", "Taka Himeno", "Nitroplus", 50000,
                "Tentang ilmuwan yang menciptakan mesin waktu",
                10, "15-10-2009", "111-111-1111-11-1", 250,
                "https://m.media-amazon.com/images/M/MV5BMjUxMzE4ZDctODNjMS00MzIwLThjNDktODkwYjc5YWU0MDc0XkEyXkFqcGdeQXVyNjc3OTE4Nzk@._V1_FMjpg_UX1000_.jpg",
                "Science Fiction", 10);

        books.add(book2);
    }

    @Test
    void testFindByIdIfFound() {
        Book book1 = books.getFirst();
        doReturn(Optional.of(book1)).when(bookDetailsRepository).findById(book1.getId());

        Optional<Book> result = bookDetailsService.findById(book1.getId());
        assertTrue(result.isPresent());
        assertEquals(book1.getId(), result.get().getId());
    }

    @Test
    void testFindByIdIfNotFound() {
        doReturn(Optional.empty()).when(bookDetailsRepository).findById(10);

        Optional<Book> result = bookDetailsService.findById(10);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetTopTenBestSellingBooksWhenMoreThanTenBooksExist() {
        // ensure there are more than ten books in the list
        Book book3 = new Book(3, "The Quantum Paradox", "Aria Kepler", "Cosmic Publishing", 75000,
                "A mind-bending journey through parallel universes and quantum entanglement.", 5,
                "20-05-2023", "222-222-2222-22-2", 320,
                "https://example.com/quantum-paradox.jpg", "Science Fiction", 120);
        books.add(book3);

        Book book4 = new Book(4, "Midnight Chronicles", "Evelyn Nightshade", "Moonlit Books", 55000,
                "A gripping fantasy saga of forbidden magic and ancient prophecies.", 8,
                "12-09-2022", "333-333-3333-33-3", 420,
                "https://example.com/midnight-chronicles.jpg", "Fantasy", 180);
        books.add(book4);

        Book book5 = new Book(5, "The Lost Constellation", "Stella Nova", "Celestial Press", 6599,
                "A cosmic adventure where constellations come alive and secrets of the universe unfold", 15,
                "05-03-2024", "444-444-4444-44-4", 380,
                "https://example.com/lost-constellation.jpg", "Science Fiction", 90);
        books.add(book5);

        Book book6 = new Book(6, "Whispers in the Mist", "Evelyn Gray", "Enigma Books", 4995,
                "A haunting mystery set in a fog-shrouded coastal town, where secrets emerge with the tide", 20,
                "18-07-2023", "555-555-5555-55-5", 300,
                "https://example.com/whispers-mist.jpg", "Mystery", 75);
        books.add(book6);

        Book book7 = new Book(7, "Clockwork Chronicles", "Victor Gearsmith", "Gears & Cogs Publishing", 7999,
                "Steampunk adventure where clockwork automatons hold the key to an ancient prophecy", 12,
                "30-11-2022", "666-666-6666-66-6", 420,
                "https://example.com/clockwork-chronicles.jpg", "Fantasy", 110);
        books.add(book7);

        Book book8 = new Book(8, "Ink and Shadows", "Luna Nightshade", "Midnight Mysteries Press", 5500,
                "Dark urban fantasy where forbidden magic intertwines with ancient grimoires", 18,
                "10-09-2023", "777-777-7777-77-7", 280,
                "https://example.com/ink-shadows.jpg", "Urban Fantasy", 95);
        books.add(book8);

        Book book9 = new Book(9, "Echoes of Eternity", "Orion Starfire", "Astral Books", 6950,
                "Time-travel romance where love transcends centuries and echoes across lifetimes", 8,
                "22-04-2024", "888-888-8888-88-8", 350,
                "https://example.com/echoes-eternity.jpg", "Romance", 60);
        books.add(book9);

        Book book10 = new Book(10, "Serpent’s Song", "Draco Wyrmwood", "Wyvern Publishing", 6000,
                "Epic fantasy saga where ancient prophecies entwine with dragon lore and forbidden alliances", 5,
                "07-12-2023", "999-999-9999-99-9", 500,
                "https://example.com/serpents-song.jpg", "Epic Fantasy", 40);
        books.add(book10);

        doReturn(books).when(bookDetailsRepository).findAll();

        List<Book> result = bookDetailsService.getTopTenBestSellingBooks();

        assertEquals(Math.min(10, books.size()), result.size());
    }

    @Test
    void testGetTopTenBestSellingBooksWhenLessThanTenBooksExist() {
        doReturn(books).when(bookDetailsRepository).findAll();

        List<Book> result = bookDetailsService.getTopTenBestSellingBooks();

        assertEquals(books.size(), result.size());
    }

}
