package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.repository.BookDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookDetailsServiceImpl implements BookDetailsService{

    @Autowired
    BookDetailsRepository bookDetailsRepository;

    @Override
    public Optional<Book> findById(int id) {
    }
}
