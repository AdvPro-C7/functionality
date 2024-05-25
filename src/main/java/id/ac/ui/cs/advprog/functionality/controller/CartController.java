package id.ac.ui.cs.advprog.functionality.controller;


import id.ac.ui.cs.advprog.functionality.dto.*;
import id.ac.ui.cs.advprog.functionality.model.Order;
import id.ac.ui.cs.advprog.functionality.service.CartService;
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

    @GetMapping("/userCart")
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

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
        return cartService.placeOrder(placeOrderDto);
    }

    @PostMapping("/orders/pay")
    public ResponseEntity<?> payForOrder(@RequestBody PaymentDto paymentDto) {
        ResponseEntity<?> response = cartService.payForOrder(paymentDto);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/orders/cancel")
    public ResponseEntity<?> cancelOrder(@RequestBody PaymentDto paymentDto) {
        ResponseEntity<?> response = cartService.cancelOrder(paymentDto);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }


   @GetMapping("/orders/waiting-shipping")
   public ResponseEntity<List<Order>> getOrdersWaitingShipping(@RequestBody UserDto userDto) {
       Long userId = userDto.getUserId();
       List<Order> orders = cartService.getOrdersWaitingShipping(userId);
       return ResponseEntity.ok(orders);
   }

   @GetMapping("/orders/waiting-payment")
   public ResponseEntity<List<Order>> getOrdersWaitingPayment(@RequestBody UserDto userDto) {
       Long userId = userDto.getUserId();
       List<Order> orders = cartService.getOrdersWaitingPayment(userId);
       return ResponseEntity.ok(orders);
   }

}