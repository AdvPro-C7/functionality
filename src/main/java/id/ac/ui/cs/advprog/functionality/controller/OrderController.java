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
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
        return orderService.placeOrder(placeOrderDto);
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payForOrder(@RequestBody PaymentDto paymentDto) {
        ResponseEntity<?> response = orderService.payForOrder(paymentDto);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelOrder(@RequestBody PaymentDto paymentDto) {
        ResponseEntity<?> response = orderService.cancelOrder(paymentDto);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }


    @PostMapping("/waiting-shipping")
    public ResponseEntity<List<Order>> getOrdersWaitingShipping(@RequestBody UserDto userDto) {
        Long userId = userDto.getUserId();
        List<Order> orders = orderService.getOrdersWaitingShipping(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/waiting-payment")
    public ResponseEntity<List<Order>> getOrdersWaitingPayment(@RequestBody UserDto userDto) {
        Long userId = userDto.getUserId();
        List<Order> orders = orderService.getOrdersWaitingPayment(userId);
        return ResponseEntity.ok(orders);
    }


}