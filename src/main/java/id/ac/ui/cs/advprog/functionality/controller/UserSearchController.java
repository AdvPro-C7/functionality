package id.ac.ui.cs.advprog.functionality.controller;


import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.User;
import id.ac.ui.cs.advprog.functionality.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserSearchController {

    @Autowired
    UserSearchService userSearchService;

    @RequestMapping(value = "/api/user/", method = RequestMethod.GET)
    public ResponseEntity getAllUser(String data){
        ResponseEntity responseEntity = null;
        try {
            List<User> userByName = userSearchService.findByName(data);
            List<User> userByEmail = userSearchService.findByEmail(data);
            List<User> combinedList = new ArrayList<>(userByName);
            combinedList.addAll(userByEmail);
            responseEntity = ResponseEntity.ok(combinedList);
        } catch (Exception e){
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
