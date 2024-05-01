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

import java.util.Optional;

@RestController
public class BookDetailsController {

    @Autowired
    BookDetailsService bookDetailsService;

    @RequestMapping(value = "/api/book-details/{id}", method = RequestMethod.GET)
    public ResponseEntity getDetailsBook(@PathVariable int id){
        ResponseEntity responseEntity = null;
        try {
            Optional<Book> book = bookDetailsService.findById(id);
            responseEntity = ResponseEntity.ok(book);
        } catch (Exception e){
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
