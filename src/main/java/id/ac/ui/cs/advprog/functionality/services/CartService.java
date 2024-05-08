package id.ac.ui.cs.advprog.functionality.services;

import id.ac.ui.cs.advprog.functionality.dto.AddBookCartDto;
import id.ac.ui.cs.advprog.functionality.dto.OrderDto;
import id.ac.ui.cs.advprog.functionality.dto.PaymentDto;
import id.ac.ui.cs.advprog.functionality.dto.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    ResponseEntity<?> addBookToCart(AddBookCartDto addBookCartDto);
    ResponseEntity<?> createCart(Long userId);
    OrderDto getCartByUserId(Long userId);
    OrderDto increaseProductQuantity(AddBookCartDto addBookCartDto);
    OrderDto decreaseProductQuantity(AddBookCartDto addBookCartDto);
    ResponseEntity<?> deleteCartItem(Long cartItemId);
    OrderDto placeOrder(PlaceOrderDto placeOrderDto);
    ResponseEntity<?> payForOrder(PaymentDto paymentDto);
    ResponseEntity<?> cancelOrder(PaymentDto paymentDto) ;
    List<OrderDto> getOrdersWaitingShipping(Long userId);
    List<OrderDto> getOrdersWaitingPayment(Long userId);
}
