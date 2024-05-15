package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.User;
import id.ac.ui.cs.advprog.functionality.repository.UserSearchByEmailRepository;
import id.ac.ui.cs.advprog.functionality.repository.UserSearchByNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchServiceImpl implements UserSearchService{

    @Autowired
    UserSearchByNameRepository userSearchByNameRepository;

    @Autowired
    UserSearchByEmailRepository userSearchByEmailRepository;

    @Override
    public List<User> findByEmail (String email){
        return userSearchByEmailRepository.findByEmailStartingWith(email);
    }

    @Override
    public List<User> findByName (String name){
        return userSearchByNameRepository.findByNamaStartingWith(name);
    }


}
