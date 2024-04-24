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