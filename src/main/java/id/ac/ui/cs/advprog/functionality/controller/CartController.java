package id.ac.ui.cs.advprog.functionality.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.functionality.model.Cart;
import id.ac.ui.cs.advprog.functionality.model.CartItem;
import id.ac.ui.cs.advprog.functionality.service.CartService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addItem/{cartId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Integer cartId, @RequestBody CartItem cartItem) {
        try {
            Cart cart = cartService.addItemToCart(cartId, cartItem);
            return ResponseEntity.ok(cart);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteItem/{cartId}")
    public ResponseEntity<Boolean> deleteItemFromCart(@PathVariable Integer cartId, @RequestBody CartItem cartItem) {
        try {
            boolean deleted = cartService.deleteItemFromCart(cartId, cartItem);
            return ResponseEntity.ok(deleted);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteAllItems/{cartId}")
    public ResponseEntity<Boolean> deleteAllItemsFromCart(@PathVariable Integer cartId) {
        try {
            boolean deleted = cartService.deleteAllItemsFromCart(cartId);
            return ResponseEntity.ok(deleted);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findCart/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Integer userId) {
        try {
            Cart cart = cartService.findCartByUserId(userId);
            return ResponseEntity.ok(cart);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Integer userId) {
        try {
            Cart cart = cartService.createCart(userId);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
