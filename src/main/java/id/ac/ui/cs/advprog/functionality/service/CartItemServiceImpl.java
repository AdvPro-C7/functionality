package id.ac.ui.cs.advprog.functionality.service;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.functionality.model.Book;
import id.ac.ui.cs.advprog.functionality.model.CartItem;
import id.ac.ui.cs.advprog.functionality.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService{
    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem createCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
        return cartItem;
    }

    public boolean deleteCartItem(CartItem cartItem) {

        if(cartItemRepository.findById(cartItem.getId()).isPresent()){
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    public List<CartItem> getAllCartItems() {
        Iterable<CartItem> cartItems = cartItemRepository.findAll();
        List<CartItem> cartItemList = new ArrayList<>();
        cartItems.forEach(cartItem -> cartItemList.add(cartItem));
        return cartItemList;
    }
}
