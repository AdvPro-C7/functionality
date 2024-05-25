package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.dto.*;
import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import id.ac.ui.cs.advprog.functionality.model.CartItems;
import id.ac.ui.cs.advprog.functionality.model.Order;
import id.ac.ui.cs.advprog.functionality.repository.CartItemRepository;
import id.ac.ui.cs.advprog.functionality.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> addBookToCart(AddBookCartDto addBookCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndStatus(addBookCartDto.getUserId(), OrderStatus.PENDING);
        Optional<CartItems> optionalCartItems = cartItemRepository.findByBookIdAndOrderIdAndUserId(
                addBookCartDto.getBookId(), activeOrder.getId(), addBookCartDto.getUserId());
        String bookServiceUrl = "http://localhost:8081/api/book-details/" + addBookCartDto.getBookId();
        ResponseEntity<BookDto> bookResponse = restTemplate.getForEntity(bookServiceUrl, BookDto.class);

        if (optionalCartItems.isPresent()) {
            CartItems existingCartItem = optionalCartItems.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + addBookCartDto.getQuantity());
            cartItemRepository.save(existingCartItem);

            activeOrder.setTotalPrice(
                    activeOrder.getTotalPrice() + (existingCartItem.getPrice() * addBookCartDto.getQuantity()));
            orderRepository.save(activeOrder);

            return ResponseEntity.ok("Book quantity updated in the cart");
        } else {
            if (bookResponse.getStatusCode() == HttpStatus.OK) {
                BookDto book = bookResponse.getBody();
                if (book != null) {
                    CartItems cart = new CartItems();
                    cart.setBookId(book.getId());
                    cart.setQuantity(addBookCartDto.getQuantity());
                    cart.setBookTitle(book.getTitle());
                    cart.setAuthor(book.getAuthor());
                    cart.setPrice(book.getPrice());
                    cart.setUserId(addBookCartDto.getUserId());
                    cart.setOrder(activeOrder);

                    cartItemRepository.save(cart);

                    activeOrder.setTotalPrice(activeOrder.getTotalPrice() + cart.getPrice());
                    activeOrder.getCartItems().add(cart);

                    orderRepository.save(activeOrder);

                    return ResponseEntity.status(HttpStatus.CREATED).body(book);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Book is missing contact admin");
                }

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

    public ResponseEntity<?> createCart(Long userId) {
        Order activeOrder = orderRepository.findByUserIdAndStatus(userId, OrderStatus.PENDING);
        if (activeOrder != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cart with that user id already created");
        }
        Order newOrder = new Order();
        newOrder.setStatus(OrderStatus.PENDING);
        newOrder.setTotalPrice(0.0);
        newOrder.setUserId(userId);
        newOrder.setCartItems(new ArrayList<>());
        Order savedOrder = orderRepository.save(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    public ResponseEntity<?> getCartByUserId(Long userId) {
        Order activeOrder = orderRepository.findByUserIdAndStatus(userId, OrderStatus.PENDING);
        if (activeOrder != null) {
            return ResponseEntity.status(HttpStatus.OK).body(activeOrder);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<?> increaseProductQuantity(AddBookCartDto addBookCartDto) {
        Order userOrder = orderRepository.findByUserIdAndStatus(addBookCartDto.getUserId(), OrderStatus.PENDING);

        Optional<CartItems> optionalCartItems = cartItemRepository.findByBookIdAndOrderIdAndUserId(
                addBookCartDto.getBookId(), userOrder.getId(), addBookCartDto.getUserId());

        String bookServiceUrl = "http://localhost:8081/api/book-details/" + addBookCartDto.getBookId();
        ResponseEntity<BookDto> bookResponse = restTemplate.getForEntity(bookServiceUrl, BookDto.class);

        if (optionalCartItems.isPresent()) {
            if (bookResponse.getStatusCode() == HttpStatus.OK) {
                BookDto book = bookResponse.getBody();
                if (book != null) {
                    CartItems cartItem = optionalCartItems.get();
                    userOrder.setTotalPrice(userOrder.getTotalPrice() + book.getPrice());

                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    cartItemRepository.save(cartItem);

                    return ResponseEntity.status(HttpStatus.OK).body(userOrder);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Book is missing contact admin");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //
    public ResponseEntity<?> decreaseProductQuantity(AddBookCartDto addBookCartDto) {
        Order userOrder = orderRepository.findByUserIdAndStatus(addBookCartDto.getUserId(), OrderStatus.PENDING);

        Optional<CartItems> optionalCartItems = cartItemRepository.findByBookIdAndOrderIdAndUserId(
                addBookCartDto.getBookId(), userOrder.getId(), addBookCartDto.getUserId());

        String bookServiceUrl = "http://localhost:8081/api/book-details/" + addBookCartDto.getBookId();
        ResponseEntity<BookDto> bookResponse = restTemplate.getForEntity(bookServiceUrl, BookDto.class);

        if (optionalCartItems.isPresent()) {
            if (bookResponse.getStatusCode() == HttpStatus.OK) {
                BookDto book = bookResponse.getBody();
                if (book != null) {
                    CartItems cartItem = optionalCartItems.get();
                    userOrder.setTotalPrice(userOrder.getTotalPrice() + book.getPrice());

                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    cartItemRepository.save(cartItem);

                    return ResponseEntity.status(HttpStatus.OK).body(userOrder);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Book is missing contact admin");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> deleteCartItem(Long cartItemId) {
        Optional<CartItems> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isPresent()) {
            CartItems cartItem = optionalCartItem.get();
            Order order = cartItem.getOrder();

            order.setTotalPrice(order.getTotalPrice() - (cartItem.getPrice() *
                    cartItem.getQuantity()));
            order.getCartItems().remove(cartItem);

            cartItemRepository.delete(cartItem);

            orderRepository.save(order);

            return ResponseEntity.status(HttpStatus.OK).body("Cart item deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found");
        }
    }

    public ResponseEntity<?> placeOrder(PlaceOrderDto placeOrderDto) {
        Order activeOrder = orderRepository.findByUserIdAndStatus(placeOrderDto.getUserId(),
                OrderStatus.PENDING);

        if (activeOrder != null) {
            activeOrder.setShippingAddress(placeOrderDto.getAddress());
            activeOrder.setOrderDate(new Date());
            activeOrder.setStatus(OrderStatus.WAITING_PAYMENT);
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
     return orderRepository.findAllByUserIdAndStatus(userId,OrderStatus.WAITING_SHIPPING);
     }

     public List<Order> getOrdersWaitingPayment(Long userId) {
        return orderRepository.findAllByUserIdAndStatus(userId,OrderStatus.WAITING_PAYMENT);
    }
}