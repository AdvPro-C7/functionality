package id.ac.ui.cs.advprog.functionality.model;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    private List<Book> books;

    public BookList() {
        this.books = new ArrayList<>();
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