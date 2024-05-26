package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.dto.PaymentDto;
import id.ac.ui.cs.advprog.functionality.dto.PlaceOrderDto;
import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import id.ac.ui.cs.advprog.functionality.model.Order;
import id.ac.ui.cs.advprog.functionality.repository.CartItemRepository;
import id.ac.ui.cs.advprog.functionality.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<?> placeOrder(PlaceOrderDto placeOrderDto) {
        Order activeOrder = orderRepository.findByUserIdAndStatus(placeOrderDto.getUserId(),
                OrderStatus.PENDING);

        if (activeOrder != null) {
            activeOrder.setShippingAddress(placeOrderDto.getAddress());
            activeOrder.setOrderDate(new Date());
            activeOrder.setStatus(OrderStatus.WAITING_PAYMENT);
            activeOrder.setCartItems(activeOrder.getCartItems());
            orderRepository.save(activeOrder);

            Order newOrder = new Order();
            newOrder.setStatus(OrderStatus.PENDING);
            newOrder.setTotalPrice(0.0);
            newOrder.setUserId(placeOrderDto.getUserId());

            orderRepository.save(newOrder);

            return ResponseEntity.status(HttpStatus.CREATED).body(activeOrder);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
    }

    public ResponseEntity<?> payForOrder(PaymentDto paymentDto) {
        Optional<Order> optionalOrder = orderRepository.findById(paymentDto.getOrderId());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (!order.getUserId().equals(paymentDto.getUserId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not authorized to pay for this order");
            }

            if (order.getStatus() != OrderStatus.WAITING_PAYMENT) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not waiting for payment");
            }

            order.setStatus(OrderStatus.WAITING_SHIPPING);
            orderRepository.save(order);

            return ResponseEntity.status(HttpStatus.OK).body("Payment successful");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }

    public ResponseEntity<?> cancelOrder(@RequestBody PaymentDto paymentDto) {
        Optional<Order> optionalOrder = orderRepository.findById(paymentDto.getOrderId());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (!order.getUserId().equals(paymentDto.getUserId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not authorized to cancel this order");
            }

            if (order.getStatus() != OrderStatus.WAITING_PAYMENT) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Order cannot be canceled as it is not waiting for payment");
            }

            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);

            return ResponseEntity.status(HttpStatus.OK).body("Order canceled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }

    public List<Order> getOrdersWaitingShipping(Long userId) {
        return orderRepository.findAllByUserIdAndStatus(userId, OrderStatus.WAITING_SHIPPING);
    }

    public List<Order> getOrdersWaitingPayment(Long userId) {
        return orderRepository.findAllByUserIdAndStatus(userId, OrderStatus.WAITING_PAYMENT);
    }

}
