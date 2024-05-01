package id.ac.ui.cs.advprog.functionality.controller;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-list")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getBookList() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> searchBooks(@RequestParam("keyword") String keyword) {
        List<Book> books = bookService.searchBooks(keyword);
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> sortBooks(@RequestParam("criteria") String criteria) {
        List<Book> books;
        switch (criteria) {
            case "newest":
                books = bookService.findBooksByNewest();
                break;
            case "popularity":
                books = bookService.findBooksByPopularity();
                break;
            case "priceAsc":
                books = bookService.findBooksByPriceAsc();
                break;
            case "priceDesc":
                books = bookService.findBooksByPriceDesc();
                break;
            default:
                books = bookService.findAllBooks();
            }
            return ResponseEntity.ok(books);
        }

    @GetMapping(value = "/search-sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> searchAndSortBooks(@RequestParam("keyword") String keyword, @RequestParam("sortBy") String sortBy) {
        List<Book> books = bookService.searchAndSortBooks(keyword, sortBy);
        return ResponseEntity.ok(books);
    }
    
    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBookDetails(@PathVariable("id") int id) {
        Book book = bookService.getBookByIndex(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }
} 
       