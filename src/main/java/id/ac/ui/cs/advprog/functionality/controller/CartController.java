package id.ac.ui.cs.advprog.functionality.controller;

import id.ac.ui.cs.advprog.functionality.model.Cart;
import id.ac.ui.cs.advprog.functionality.model.CartItem;
import id.ac.ui.cs.advprog.functionality.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addItem")
    public Cart addItemToCart(@RequestParam Integer cartId, @RequestBody CartItem cartItem) {
        return cartService.addItemToCart(cartId, cartItem);
    }

    @PostMapping("/deleteItem")
    public boolean deleteItemFromCart(@RequestParam Integer cartId, @RequestBody CartItem cartItem) {
        return cartService.deleteItemFromCart(cartId, cartItem);
    }

    @PostMapping("/deleteAllItems")
    public boolean deleteAllItemsFromCart(@RequestParam Integer cartId) {
        return cartService.deleteAllItemsFromCart(cartId);
    }

    @GetMapping("/findCart")
    public Cart findCartByUserId(@RequestParam Integer userId) {
        return cartService.findCartByUserId(userId);
    }

    @PostMapping("/create")
    public Cart createCart(@RequestParam Integer userId) { 
        return cartService.createCart(userId);
    }
}
