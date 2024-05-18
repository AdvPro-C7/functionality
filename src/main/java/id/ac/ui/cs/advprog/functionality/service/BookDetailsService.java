package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.Book;
import java.util.Optional;


public interface BookDetailsService {

    public Optional<Book> findById(int id);
}
