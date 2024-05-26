package id.ac.ui.cs.advprog.functionality.controller;


import id.ac.ui.cs.advprog.functionality.dto.*;
import id.ac.ui.cs.advprog.functionality.model.Order;
import id.ac.ui.cs.advprog.functionality.service.CartService;
import id.ac.ui.cs.advprog.functionality.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddBookCartDto addBookCartDto) {
        return cartService.addBookToCart(addBookCartDto);
    }

    @PostMapping("/createCart")
    public ResponseEntity<?> createCart(@RequestBody UserDto userDto) {
        Long userId = userDto.getUserId();
        return cartService.createCart(userId);
    }

    @PostMapping("/userCart")
    public ResponseEntity<?> getCartByUserId(@RequestBody UserDto userDto) {
        Long userId = userDto.getUserId();
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/addition")
    public ResponseEntity<?> increaseBookQuantity(@RequestBody AddBookCartDto addBookCartDto) {
        return cartService.increaseProductQuantity(addBookCartDto);
    }

    @PostMapping("/deduction")
    public ResponseEntity<?> decreaseBookQuantity(@RequestBody AddBookCartDto addBookCartDto) {
        return cartService.decreaseProductQuantity(addBookCartDto);
    }

    @DeleteMapping("/cart/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long cartItemId) {
        return cartService.deleteCartItem(cartItemId);
    }

}