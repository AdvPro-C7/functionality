package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.enums.BookSortCriteria;
import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByKeyword(keyword);
    }

    @Override
    public List<Book> findBooksByNewest() {
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "publishDate"));
    }

    @Override
    public List<Book> findBooksByPopularity() {
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "sold"));
    }

    @Override
    public List<Book> findBooksByPriceAsc() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    @Override
    public List<Book> findBooksByPriceDesc() {
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

    @Override
    public List<Book> searchAndSortBooks(String keyword, BookSortCriteria sortBy) {
        return bookRepository.searchAndSortBooks(keyword, sortBy.getSortKey());
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean isRepositoryEmpty() {
        return bookRepository.count() == 0;
    }
}
