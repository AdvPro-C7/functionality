package id.ac.ui.cs.advprog.functionality.controller;

import id.ac.ui.cs.advprog.functionality.model.User;
import id.ac.ui.cs.advprog.functionality.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin()
@RestController
public class UserSearchController {

    @Autowired
    UserSearchService userSearchService;

    @RequestMapping(value = "/users/search", method = RequestMethod.GET)
    public List<User> searchUsers(@RequestParam(required = false) String nama) {
        Set<User> users = new HashSet<>();

        List<User> userName = userSearchService.findByName(nama);
        users.addAll(userName);

        List<User> userEmail = userSearchService.findByEmail(nama);
        users.addAll(userEmail);

        return new ArrayList<>(users);

    }



}