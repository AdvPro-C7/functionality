package id.ac.ui.cs.advprog.functionality.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.Cart;
import id.ac.ui.cs.advprog.functionality.model.CartItem;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Integer> {

    
}