package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.User;

import java.util.List;

public interface UserSearchService {

    public List<User> findByEmail(String email);

    public List<User> findByName(String name);
    public User findById(Long id);


}

