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

import java.util.*;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> addBookToCart(AddBookCartDto addBookCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndStatus(addBookCartDto.getUserId(), OrderStatus.PENDING);
        Optional<CartItems> optionalCartItems = cartItemRepository.findByBookIdAndOrderIdAndUserId(
                addBookCartDto.getBookId(),activeOrder.getId(),addBookCartDto.getUserId()
        );
        if(optionalCartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book is already in the cart");
        }else{
            Optional<Book> optionalBook = bookRepository.findById(addBookCartDto.getBookId());
            Optional<User> optionalUser = userRepository.findById(addBookCartDto.getUserId());

            if(optionalBook.isPresent()&& optionalUser.isPresent()){
                CartItems cart = new CartItems();
                cart.setBook(optionalBook.get());
                cart.setPrice(optionalBook.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                cartItemRepository.save(cart);

                activeOrder.setTotalPrice(activeOrder.getTotalPrice()+cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body("Book added to cart successfully");
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
    }

    public ResponseEntity<?> createCart(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Order newOrder = new Order();
            newOrder.setStatus(OrderStatus.PENDING);
            newOrder.setTotalPrice(0L);
            newOrder.setUser(optionalUser.get());
            newOrder.setCartItems(new ArrayList<>());
            Order savedOrder = orderRepository.save(newOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public OrderDto getCartByUserId(Long userId){
        Order activeOrder = orderRepository.findByUserIdAndStatus(userId, OrderStatus.PENDING);
        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto)
                .toList();
        OrderDto orderDto = new OrderDto();
        orderDto.setTotalPrice(activeOrder.getTotalPrice());
        orderDto.setId(activeOrder.getId());
        orderDto.setStatus(activeOrder.getStatus());
        orderDto.setCartItems(cartItemsDtoList);
        return orderDto;
    }

    public OrderDto increaseProductQuantity(AddBookCartDto addBookCartDto){
        Order activeOrder = orderRepository.findByUserIdAndStatus(addBookCartDto.getUserId(), OrderStatus.PENDING);
        Optional<Book> optionalBook = bookRepository.findById(addBookCartDto.getBookId());
        Optional<CartItems> optionalCartItems = cartItemRepository.findByBookIdAndOrderIdAndUserId(
                addBookCartDto.getBookId(),activeOrder.getId(),addBookCartDto.getUserId()
        );

        if(optionalBook.isPresent() && optionalCartItems.isPresent()){
            CartItems cartItem = optionalCartItems.get();
            Book book = optionalBook.get();
            activeOrder.setTotalPrice(activeOrder.getTotalPrice()+book.getPrice());

            cartItem.setQuantity(cartItem.getQuantity()+1);
            cartItemRepository.save(cartItem);
        }
        return null;
    }

    public OrderDto decreaseProductQuantity(AddBookCartDto addBookCartDto){
        Order activeOrder = orderRepository.findByUserIdAndStatus(addBookCartDto.getUserId(), OrderStatus.PENDING);
        Optional<Book> optionalBook = bookRepository.findById(addBookCartDto.getBookId());
        Optional<CartItems> optionalCartItems = cartItemRepository.findByBookIdAndOrderIdAndUserId(
                addBookCartDto.getBookId(),activeOrder.getId(),addBookCartDto.getUserId()
        );

        if(optionalBook.isPresent() && optionalCartItems.isPresent()){
            CartItems cartItem = optionalCartItems.get();
            Book book = optionalBook.get();
            activeOrder.setTotalPrice(activeOrder.getTotalPrice()-book.getPrice());

            cartItem.setQuantity(cartItem.getQuantity()-1);
            cartItemRepository.save(cartItem);
        }
        return null;
    }

    public ResponseEntity<?> deleteCartItem(Long cartItemId) {
        Optional<CartItems> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isPresent()) {
            CartItems cartItem = optionalCartItem.get();
            Order order = cartItem.getOrder();

            order.setTotalPrice(order.getTotalPrice() - (cartItem.getPrice() * cartItem.getQuantity()));
            order.getCartItems().remove(cartItem);

            cartItemRepository.delete(cartItem);

            orderRepository.save(order);

            return ResponseEntity.status(HttpStatus.OK).body("Cart item deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found");
        }
    }

    public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
        Order activeOrder = orderRepository.findByUserIdAndStatus(placeOrderDto.getUserId(), OrderStatus.PENDING);
        Optional<User> optionalUser = userRepository.findById(placeOrderDto.getUserId());


        if (optionalUser.isPresent() && activeOrder != null) {
            activeOrder.setShippingAddress(placeOrderDto.getAddress());
            activeOrder.setOrderDate(new Date());
            activeOrder.setStatus(OrderStatus.WAITING_PAYMENT);
            orderRepository.save(activeOrder);

            Order newOrder = new Order();
            newOrder.setStatus(OrderStatus.PENDING);
            newOrder.setTotalPrice(0L);
            newOrder.setUser(optionalUser.get());

            orderRepository.save(newOrder);

            return activeOrder.getOrderDto();

        }
        return null;
    }

    public ResponseEntity<?> payForOrder(PaymentDto paymentDto) {
        Optional<Order> optionalOrder = orderRepository.findById(paymentDto.getOrderId());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (!order.getUser().getId().equals(paymentDto.getUserId())) {
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
            if (!order.getUser().getId().equals(paymentDto.getUserId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not authorized to cancel this order");
            }

            if (order.getStatus() != OrderStatus.WAITING_PAYMENT) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order cannot be canceled as it is not waiting for payment");
            }

            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);

            return ResponseEntity.status(HttpStatus.OK).body("Order canceled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }


    public List<OrderDto> getOrdersWaitingShipping(Long userId) {
        return orderRepository.findAllByUserIdAndStatus(userId, OrderStatus.WAITING_SHIPPING)
                .stream()
                .map(Order::getOrderDto)
                .toList();
    }

    public List<OrderDto> getOrdersWaitingPayment(Long userId) {
        return orderRepository.findAllByUserIdAndStatus(userId, OrderStatus.WAITING_PAYMENT)
                .stream()
                .map(Order::getOrderDto)
                .toList();
    }

}