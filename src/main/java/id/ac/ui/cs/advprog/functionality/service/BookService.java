package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.enums.BookSortCriteria;
import id.ac.ui.cs.advprog.functionality.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBooks();
    List<Book> searchBooks(String keyword);
    List<Book> findBooksByNewest();
    List<Book> findBooksByPopularity();
    List<Book> findBooksByPriceAsc();
    List<Book> findBooksByPriceDesc();
    List<Book> searchAndSortBooks(String keyword, BookSortCriteria sortBy);
    Optional<Book> getBookById(int id);
    boolean isRepositoryEmpty();
}
