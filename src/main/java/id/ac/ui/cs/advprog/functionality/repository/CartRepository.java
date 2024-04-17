package id.ac.ui.cs.advprog.functionality.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.functionality.model.Cart;
import java.util.Optional;
@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>{
    
}
