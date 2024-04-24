package id.ac.ui.cs.advprog.functionality.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.functionality.model.CartItem;

import java.util.Optional;


@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Integer>{

}