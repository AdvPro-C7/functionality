package id.ac.ui.cs.advprog.functionality.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.functionality.model.Cart;
import id.ac.ui.cs.advprog.functionality.model.CartItem;

import id.ac.ui.cs.advprog.functionality.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart createCart(Integer userId) {
        if(cartRepository.findById(userId).isEmpty()){
            Cart cart = new Cart();
            cart.setId(userId);
            cartRepository.save(cart);
            return cart;
        }
        return null;
    }

    public Cart addItemToCart(Integer cartId,CartItem cartItem){
        Optional<Cart> cartRetrieved = cartRepository.findById(cartId);
        if(cartRetrieved.isPresent()){
            cartRetrieved.get().getCartItems().add(cartItem);
            cartRepository.save(cartRetrieved.get());
            return cartRetrieved.get();
        }

        return null;
    }
    public boolean deleteItemFromCart(Integer cartId,CartItem cartItem){
        Optional<Cart> cartRetrieved = cartRepository.findById(cartId);
        if(cartRetrieved.isPresent()){
            cartRetrieved.get().getCartItems().remove(cartItem);
            cartRepository.save(cartRetrieved.get());
            return true;
        }
        return false;
    }
    public boolean deleteAllItemsFromCart(Integer cartId){
        Optional<Cart> cartRetrieved = cartRepository.findById(cartId);
        if(cartRetrieved.isPresent()){
            cartRetrieved.get().getCartItems().clear();
            cartRepository.save(cartRetrieved.get());
            return true;
        }
        return false;
    }
    public Cart findCartByUserId(Integer CartId){
        Optional<Cart> cart = cartRepository.findById(CartId);
        if(cart.isPresent()){
            return cart.get();
        }
        return null;
    }

}
