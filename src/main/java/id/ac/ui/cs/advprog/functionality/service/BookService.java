package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.Book;
import java.util.List;

public interface BookService {
    List<Book> findAllBooks();
    List<Book> searchBooks(String keyword);
    List<Book> findBooksByNewest();
    List<Book> findBooksByPopularity();
    List<Book> findBooksByPriceAsc();
    List<Book> findBooksByPriceDesc();
    List<Book> searchAndSortBooks(String keyword, String sortBy);
    Book getBookByIndex(int index);
    boolean isRepositoryEmpty();
    String getEmptyRepositoryMessage();
}