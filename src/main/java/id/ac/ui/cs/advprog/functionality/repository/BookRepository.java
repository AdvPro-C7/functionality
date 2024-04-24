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
    }

    public List<Book> sortBooksByNewest() {
    }
    
    public List<Book> sortBooksByPopularity() {
    }
    
    public List<Book> sortBooksByPriceAsc() {
    }
    
    public List<Book> sortBooksByPriceDesc() {
    }
    
    public List<Book> searchAndSortBooks(String keyword, String sortBy) {
    }
    
    public List<Book> getBooks() {
    }

    public Book getBookByIdx(int index) {
    }

    public boolean isEmpty() {
    }

    public String getEmptyMessage() {
    }
}