package id.ac.ui.cs.advprog.functionality.controller;

import id.ac.ui.cs.advprog.functionality.model.CartItem;
import id.ac.ui.cs.advprog.functionality.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/create")
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.createCartItem(cartItem);
    }

    @PostMapping("/delete")
    public boolean deleteCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.deleteCartItem(cartItem);
    }
}
