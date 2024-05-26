package id.ac.ui.cs.advprog.functionality.service;
import id.ac.ui.cs.advprog.functionality.dto.AddBookCartDto;
import id.ac.ui.cs.advprog.functionality.dto.PaymentDto;
import id.ac.ui.cs.advprog.functionality.dto.PlaceOrderDto;
import id.ac.ui.cs.advprog.functionality.model.Order;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    ResponseEntity<?> addBookToCart(AddBookCartDto addBookCartDto);
    ResponseEntity<?> createCart(Long userId);
    ResponseEntity<?> getCartByUserId(Long userId);
    ResponseEntity<?> increaseProductQuantity(AddBookCartDto addBookCartDto);
    ResponseEntity<?> decreaseProductQuantity(AddBookCartDto addBookCartDto);
    ResponseEntity<?> deleteCartItem(Long cartItemId);
}