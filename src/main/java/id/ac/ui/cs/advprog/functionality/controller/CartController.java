package id.ac.ui.cs.advprog.functionality.controller;


import id.ac.ui.cs.advprog.functionality.dto.AddBookCartDto;
import id.ac.ui.cs.advprog.functionality.dto.OrderDto;
import id.ac.ui.cs.advprog.functionality.dto.PaymentDto;
import id.ac.ui.cs.advprog.functionality.dto.PlaceOrderDto;
import id.ac.ui.cs.advprog.functionality.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/createCart/{userId}")
    public ResponseEntity<?> createCart(@PathVariable Long userId) {
        return cartService.createCart(userId);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        OrderDto orderDto = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @PostMapping("/addition")
    public ResponseEntity<OrderDto> increaseBookQuantity(@RequestBody AddBookCartDto addBookCartDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addBookCartDto));
    }

    @PostMapping("/deduction")
    public ResponseEntity<OrderDto> decreaseBookQuantity(@RequestBody AddBookCartDto addBookCartDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addBookCartDto));
    }

    @DeleteMapping("/cart/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long cartItemId) {
        return cartService.deleteCartItem(cartItemId);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
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


    @GetMapping("/orders/waiting-shipping/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersWaitingShipping(@PathVariable Long userId) {
        List<OrderDto> orders = cartService.getOrdersWaitingShipping(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/waiting-payment/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersWaitingPayment(@PathVariable Long userId) {
        List<OrderDto> orders = cartService.getOrdersWaitingPayment(userId);
        return ResponseEntity.ok(orders);
    }

}
