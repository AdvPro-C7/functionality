package id.ac.ui.cs.advprog.functionality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/book-list")
public class BookController {
    
    @GetMapping("")
    public String getBookListPage(Model model) {
        return "BookListPage";
    }
}