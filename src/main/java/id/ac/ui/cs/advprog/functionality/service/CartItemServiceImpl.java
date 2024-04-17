package id.ac.ui.cs.advprog.functionality.service;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.CartItem;
import id.ac.ui.cs.advprog.functionality.repository.CartItemRepository;

public class CartItemServiceImpl {
    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem createCartItem(CartItem cartItem) {
        if(cartItemRepository.findById(cartItem.getId())!=null){
            cartItemRepository.save(cartItem);
            return cartItem;
        }
        return null;
    }

    public boolean deleteCartItem(CartItem cartItem) {
        return false;
    }
}
