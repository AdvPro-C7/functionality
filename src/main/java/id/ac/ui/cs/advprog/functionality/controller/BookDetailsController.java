package id.ac.ui.cs.advprog.functionality.controller;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.service.BookDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class BookDetailsController {

    @Autowired
    BookDetailsService bookDetailsService;

    @RequestMapping(value = "/api/book-details/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetailsBook(@PathVariable int id){
        ResponseEntity responseEntity = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/book-details/" + id;
            ResponseEntity<Book> response = restTemplate.getForEntity(url, Book.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                Book book = response.getBody();
                return ResponseEntity.ok(book);
            } else {
                return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
