package id.ac.ui.cs.advprog.functionality.service;

import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.CartItem;

@Service
public interface CartItemService {
    public CartItem create(Book book, int quantity);
    public boolean delete(CartItem cartItem);
}
