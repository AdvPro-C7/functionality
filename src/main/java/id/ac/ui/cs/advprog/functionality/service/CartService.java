package id.ac.ui.cs.advprog.functionality.service;

import java.util.List;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.functionality.model.Cart;
import id.ac.ui.cs.advprog.functionality.model.CartItem;

@Service
public interface CartService {
    public Cart createCart(Integer userId);
    public Cart addItemToCart(Integer cartId,CartItem cartItem); 
    public boolean deleteItemFromCart(Integer cartId,CartItem cartItem);
    public boolean deleteAllItemsFromCart(Integer cartId);
    public Cart findCartByUserId(Integer userId);

}
