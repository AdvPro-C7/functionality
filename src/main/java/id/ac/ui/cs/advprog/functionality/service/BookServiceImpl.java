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
    }

    @Override
    public List<Book> searchBooks(String keyword) {
    }

    @Override
    public List<Book> findBooksByNewest() {
    }

    @Override
    public List<Book> findBooksByPopularity() {
    }

    @Override
    public List<Book> findBooksByPriceAsc() {
    }

    @Override
    public List<Book> findBooksByPriceDesc() {
    }

    @Override
    public List<Book> searchAndSortBooks(String keyword, String sortBy) {
    }

    @Override
    public Book getBookByIndex(int index) {
    }

    @Override
    public boolean isRepositoryEmpty() {
    }

    @Override
    public String getEmptyRepositoryMessage() {
    }
}
