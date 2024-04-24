package id.ac.ui.cs.advprog.functionality.service;

import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.CartItem;

import java.util.List;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public boolean deleteCartItem(CartItem cartItem);
    public List<CartItem> getAllCartItems();
}
