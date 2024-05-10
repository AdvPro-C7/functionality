package id.ac.ui.cs.advprog.functionality.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.functionality.model.Book;

@Repository
public class BookRepository {
    private List<Book> books =  new ArrayList<>();

    public BookRepository() {
        addDummyBooks();
    }

    // Method to add dummy books
    private void addDummyBooks() {
        books.add(new Book(1, "To Kill a Mockingbird", "Harper Lee", "HarperCollins", 12.99,
                "To Kill a Mockingbird is a novel by Harper Lee published in 1960. It was immediately successful, winning the Pulitzer Prize, and has become a classic of modern American literature.",
                100, "July 11, 1960", "9780061120084", 336,
                "to_kill_a_mockingbird.jpg", "Fiction", 50));

        books.add(new Book(2, "1984", "George Orwell", "Secker & Warburg", 9.99,
                "1984 is a dystopian social science fiction novel by English novelist George Orwell. It was published on 8 June 1949 by Secker & Warburg as Orwell's ninth and final book completed in his lifetime.",
                150, "June 8, 1949", "9780451524935", 328,
                "1984.jpg", "Science Fiction", 70));

        books.add(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald", "Charles Scribner's Sons", 10.50,
                "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
                80, "April 10, 1925", "9780743273565", 218,
                "the_great_gatsby.jpg", "Classic", 40));

        books.add(new Book(4, "The Great Gats", "F. Scott Fitzgerald", "Charles Scribner's Sons", 11.50,
                "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
                80, "April 10, 1925", "9780743273565", 218,
                "the_great_gatsby.jpg", "Classic", 40));
            
        books.add(new Book(5, "Great Gat", "F. Scott Fitzgerald", "Charles Scribner's Sons", 13.50,
                "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
                80, "April 10, 1925", "9780743273565", 218,
                "the_great_gatsby.jpg", "Classic", 40));
            
        books.add(new Book(6, "Kill Bill", "Harper Lee", "HarperCollins", 9,
                "To Kill a Mockingbird is a novel by Harper Lee published in 1960. It was immediately successful, winning the Pulitzer Prize, and has become a classic of modern American literature.",
                100, "July 11, 1960", "9780061120084", 336,
                "to_kill_a_mockingbird.jpg", "Fiction", 50));
    }

    public List<Book> searchBooks(String keyword) {
        return books.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                                    book.getPublisher().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
    }

    public List<Book> sortBooksByNewest() {
        return books.stream()
                    .sorted((b1, b2) -> b2.getPublishDate().compareTo(b1.getPublishDate()))
                    .collect(Collectors.toList());
    }
    
    public List<Book> sortBooksByPopularity() {
        return books.stream()
                    .sorted(Comparator.comparingInt(Book::getSold).reversed())
                    .collect(Collectors.toList());
    }
    
    public List<Book> sortBooksByPriceAsc() {
        return books.stream()
                    .sorted(Comparator.comparingDouble(Book::getPrice))
                    .collect(Collectors.toList());
    }
    
    public List<Book> sortBooksByPriceDesc() {
        return books.stream()
                    .sorted((b1, b2) -> Double.compare(b2.getPrice(), b1.getPrice()))
                    .collect(Collectors.toList());
    }
    
    public List<Book> searchAndSortBooks(String keyword, String sortBy) {
        Stream<Book> filteredBooks = books.stream()
            .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                            book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                            book.getPublisher().toLowerCase().contains(keyword.toLowerCase()));
    
        switch (sortBy) {
            case "newest":
                return filteredBooks.sorted((b1, b2) -> b2.getPublishDate().compareTo(b1.getPublishDate()))
                                    .collect(Collectors.toList());
            case "popularity":
                return filteredBooks.sorted(Comparator.comparingInt(Book::getSold).reversed())
                                    .collect(Collectors.toList());
            case "priceAsc":
                return filteredBooks.sorted(Comparator.comparingDouble(Book::getPrice))
                                    .collect(Collectors.toList());
            case "priceDesc":
                return filteredBooks.sorted((b1, b2) -> Double.compare(b2.getPrice(), b1.getPrice()))
                                    .collect(Collectors.toList());
            default:
                return filteredBooks.collect(Collectors.toList());
        }
    }
    
    public List<Book> getBooks() {
        return books;
    }

    public Book getBookByIdx(int index) {
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return books.isEmpty();
    }

    public String getEmptyMessage() {
        return "No books available";
    }
}