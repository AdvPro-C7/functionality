package id.ac.ui.cs.advprog.functionality.controller;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-list")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public String getBookListPage(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "BookListPage";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam("keyword") String keyword, Model model) {
        List<Book> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        return "BookListPage";
    }

    @GetMapping("/sort")
    public String sortBooks(@RequestParam("criteria") String criteria, Model model) {
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
                break;
        }
        model.addAttribute("books", books);
        return "BookListPage";
    }

    @GetMapping("/search-sort")
    public String searchAndSortBooks(@RequestParam("keyword") String keyword, @RequestParam("sortBy") String sortBy, Model model) {
        List<Book> books = bookService.searchAndSortBooks(keyword, sortBy);
        model.addAttribute("books", books);
        return "BookListPage";
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getBookDetails(@PathVariable("id") int id) {
        Book book = bookService.getBookByIndex(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }
}
