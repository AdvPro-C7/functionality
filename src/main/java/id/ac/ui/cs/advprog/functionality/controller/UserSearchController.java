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
    public ResponseEntity<List<User>> searchUsers(@RequestParam(required = false) String nama) {
        try {
            Set<User> users = new HashSet<>();

            List<User> userName = userSearchService.findByName(nama);
            users.addAll(userName);

            List<User> userEmail = userSearchService.findByEmail(nama);
            users.addAll(userEmail);

            return ResponseEntity.ok(new ArrayList<>(users));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        ResponseEntity responseEntity = null;
        try {
            User user = userSearchService.findById(id);
            if (user == null){
                responseEntity = ResponseEntity.notFound().build();
            } else {
                responseEntity = ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }



}