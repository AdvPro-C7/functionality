package id.ac.ui.cs.advprog.functionality.repository;

import id.ac.ui.cs.advprog.functionality.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSearchByNameRepository extends JpaRepository<User, String> {

    List<User> findByNamaContainingIgnoreCase(String name);
}

