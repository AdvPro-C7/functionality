package id.ac.ui.cs.advprog.functionality.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.functionality.model.Cart;
import id.ac.ui.cs.advprog.functionality.model.CartItem;
import id.ac.ui.cs.advprog.functionality.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addItem")
    public ResponseEntity<Cart> addItemToCart(@RequestParam Integer cartId, @RequestBody CartItem cartItem) {
        Cart cart = cartService.addItemToCart(cartId, cartItem);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/deleteItem")
    public ResponseEntity<Boolean> deleteItemFromCart(@RequestParam Integer cartId, @RequestBody CartItem cartItem) {
        boolean deleted = cartService.deleteItemFromCart(cartId, cartItem);
        if (deleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/deleteAllItems")
    public ResponseEntity<Boolean> deleteAllItemsFromCart(@RequestParam Integer cartId) {
        boolean deleted = cartService.deleteAllItemsFromCart(cartId);
        if (deleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findCart")
    public ResponseEntity<Cart> findCartByUserId(@RequestParam Integer userId) {
        Cart cart = cartService.findCartByUserId(userId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestParam Integer userId) { 
        Cart cart = cartService.createCart(userId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
