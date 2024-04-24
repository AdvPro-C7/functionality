package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.getBooks();
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword);
    }

    @Override
    public List<Book> findBooksByNewest() {
        return bookRepository.sortBooksByNewest();
    }

    @Override
    public List<Book> findBooksByPopularity() {
        return bookRepository.sortBooksByPopularity();
    }

    @Override
    public List<Book> findBooksByPriceAsc() {
        return bookRepository.sortBooksByPriceAsc();
    }

    @Override
    public List<Book> findBooksByPriceDesc() {
        return bookRepository.sortBooksByPriceDesc();
    }

    @Override
    public List<Book> searchAndSortBooks(String keyword, String sortBy) {
        return bookRepository.searchAndSortBooks(keyword, sortBy);
    }

    @Override
    public Book getBookByIndex(int index) {
        return bookRepository.getBookByIdx(index);
    }

    @Override
    public boolean isRepositoryEmpty() {
        return bookRepository.isEmpty();
    }

    @Override
    public String getEmptyRepositoryMessage() {
        return bookRepository.getEmptyMessage();
    }
}
