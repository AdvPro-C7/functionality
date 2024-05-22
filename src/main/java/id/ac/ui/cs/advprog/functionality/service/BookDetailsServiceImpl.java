package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.repository.BookDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookDetailsServiceImpl implements BookDetailsService {

    @Autowired
    BookDetailsRepository bookDetailsRepository;

    @Override
    public Optional<Book> findById(int id) {
        return bookDetailsRepository.findById(id);
    }

    @Override
    public List<Book> getTopTenBestSellingBooks() {
        List<Book> allBooks = (List<Book>) bookDetailsRepository.findAll();

        // sort the books by 'sold' field in descending order
        List<Book> sortedBooks = allBooks.stream()
                .sorted((b1, b2) -> Integer.compare(b2.getSold(), b1.getSold()))
                .collect(Collectors.toList());

        return sortedBooks.subList(0, Math.min(sortedBooks.size(), 10));
    }

}