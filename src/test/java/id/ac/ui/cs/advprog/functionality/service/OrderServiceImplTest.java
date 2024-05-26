package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.dto.PaymentDto;
import id.ac.ui.cs.advprog.functionality.dto.PlaceOrderDto;
import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;

import id.ac.ui.cs.advprog.functionality.model.Order;

import id.ac.ui.cs.advprog.functionality.repository.OrderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

        @Mock
        private OrderRepository orderRepository;

        @Mock
        private RestTemplate restTemplate;

        @InjectMocks
        private OrderServiceImpl orderService;

        @BeforeEach
        void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testPlaceOrder_activeOrderExists() {
                Long userId = 123L;
                PlaceOrderDto placeOrderDto = new PlaceOrderDto();
                placeOrderDto.setUserId(userId);
                placeOrderDto.setAddress("123 Test St");

                Order activeOrder = new Order();
                activeOrder.setId(1L);
                activeOrder.setUserId(userId);
                activeOrder.setStatus(OrderStatus.PENDING);
                activeOrder.setTotalPrice(100.0);
                activeOrder.setCartItems(new ArrayList<>());

                Order newOrder = new Order();
                newOrder.setUserId(userId);
                newOrder.setStatus(OrderStatus.PENDING);
                newOrder.setTotalPrice(0.0);

                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING)))
                                .thenReturn(activeOrder);
                when(orderRepository.save(any(Order.class)))
                                .thenReturn(activeOrder)
                                .thenReturn(newOrder);

                ResponseEntity<?> responseEntity = orderService.placeOrder(placeOrderDto);

                assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
                assertEquals(activeOrder, responseEntity.getBody());

                verify(orderRepository, times(1)).findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING));
                verify(orderRepository, times(2)).save(any(Order.class)); // Once for activeOrder, once for newOrder
        }

        @Test
        public void testPlaceOrder_noActiveOrder() {
                Long userId = 123L;
                PlaceOrderDto placeOrderDto = new PlaceOrderDto();
                placeOrderDto.setUserId(userId);
                placeOrderDto.setAddress("123 Test St");

                when(orderRepository.findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING)))
                                .thenReturn(null);

                ResponseEntity<?> responseEntity = orderService.placeOrder(placeOrderDto);

                assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

                verify(orderRepository, times(1)).findByUserIdAndStatus(anyLong(), eq(OrderStatus.PENDING));
                verify(orderRepository, times(0)).save(any(Order.class)); // No save operation should be called
        }

        @Test
        public void testPayForOrder_orderExistsAndPaymentSuccessful() {
                Long userId = 123L;
                Long orderId = 1L;
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setUserId(userId);
                paymentDto.setOrderId(orderId);

                Order order = new Order();
                order.setId(orderId);
                order.setUserId(userId);
                order.setStatus(OrderStatus.WAITING_PAYMENT);

                when(orderRepository.findById(anyLong()))
                                .thenReturn(Optional.of(order));
                when(orderRepository.save(any(Order.class)))
                                .thenReturn(order);

                ResponseEntity<?> responseEntity = orderService.payForOrder(paymentDto);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals("Payment successful", responseEntity.getBody());

                verify(orderRepository, times(1)).findById(anyLong());
                verify(orderRepository, times(1)).save(any(Order.class));
        }

        @Test
        public void testPayForOrder_orderNotFound() {
                Long userId = 123L;
                Long orderId = 1L;
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setUserId(userId);
                paymentDto.setOrderId(orderId);

                when(orderRepository.findById(anyLong()))
                                .thenReturn(Optional.empty());

                ResponseEntity<?> responseEntity = orderService.payForOrder(paymentDto);

                assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
                assertEquals("Order not found", responseEntity.getBody());

                verify(orderRepository, times(1)).findById(anyLong());
                verifyNoMoreInteractions(orderRepository);
        }

        @Test
        public void testPayForOrder_userNotAuthorized() {
                Long userId = 123L;
                Long orderId = 1L;
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setUserId(userId);
                paymentDto.setOrderId(orderId);

                Order order = new Order();
                order.setId(orderId);
                order.setUserId(456L); // Different user ID
                order.setStatus(OrderStatus.WAITING_PAYMENT);

                when(orderRepository.findById(anyLong()))
                                .thenReturn(Optional.of(order));

                ResponseEntity<?> responseEntity = orderService.payForOrder(paymentDto);

                assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
                assertEquals("User is not authorized to pay for this order", responseEntity.getBody());

                verify(orderRepository, times(1)).findById(anyLong());
                verifyNoMoreInteractions(orderRepository);
        }

        @Test
        public void testPayForOrder_orderNotWaitingForPayment() {
                Long userId = 123L;
                Long orderId = 1L;
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setUserId(userId);
                paymentDto.setOrderId(orderId);

                Order order = new Order();
                order.setId(orderId);
                order.setUserId(userId);
                order.setStatus(OrderStatus.WAITING_SHIPPING); // Not WAITING_PAYMENT

                when(orderRepository.findById(anyLong()))
                                .thenReturn(Optional.of(order));

                ResponseEntity<?> responseEntity = orderService.payForOrder(paymentDto);

                assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
                assertEquals("Order is not waiting for payment", responseEntity.getBody());

                verify(orderRepository, times(1)).findById(anyLong());
                verifyNoMoreInteractions(orderRepository);
        }

        @Test
        public void testCancelOrder_orderExistsAndCancellationSuccessful() {
                Long userId = 123L;
                Long orderId = 1L;
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setUserId(userId);
                paymentDto.setOrderId(orderId);

                Order order = new Order();
                order.setId(orderId);
                order.setUserId(userId);
                order.setStatus(OrderStatus.WAITING_PAYMENT);

                when(orderRepository.findById(anyLong()))
                                .thenReturn(Optional.of(order));
                when(orderRepository.save(any(Order.class)))
                                .thenReturn(order);

                ResponseEntity<?> responseEntity = orderService.cancelOrder(paymentDto);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals("Order canceled successfully", responseEntity.getBody());

                verify(orderRepository, times(1)).findById(anyLong());
                verify(orderRepository, times(1)).save(any(Order.class));
        }

        @Test
        public void testCancelOrder_orderNotFound() {
                Long userId = 123L;
                Long orderId = 1L;
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setUserId(userId);
                paymentDto.setOrderId(orderId);

                when(orderRepository.findById(anyLong()))
                                .thenReturn(Optional.empty());

                ResponseEntity<?> responseEntity = orderService.cancelOrder(paymentDto);

                assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
                assertEquals("Order not found", responseEntity.getBody());

                verify(orderRepository, times(1)).findById(anyLong());
                verifyNoMoreInteractions(orderRepository);
        }

        @Test
        public void testCancelOrder_userNotAuthorized() {
                Long userId = 123L;
                Long orderId = 1L;
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setUserId(userId);
                paymentDto.setOrderId(orderId);

                Order order = new Order();
                order.setId(orderId);
                order.setUserId(456L); // Different user ID
                order.setStatus(OrderStatus.WAITING_PAYMENT);

                when(orderRepository.findById(anyLong()))
                                .thenReturn(Optional.of(order));

                ResponseEntity<?> responseEntity = orderService.cancelOrder(paymentDto);

                assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
                assertEquals("User is not authorized to cancel this order", responseEntity.getBody());

                verify(orderRepository, times(1)).findById(anyLong());
                verifyNoMoreInteractions(orderRepository);
        }

        @Test
        public void testCancelOrder_orderNotWaitingForPayment() {
                Long userId = 123L;
                Long orderId = 1L;
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setUserId(userId);
                paymentDto.setOrderId(orderId);

                Order order = new Order();
                order.setId(orderId);
                order.setUserId(userId);
                order.setStatus(OrderStatus.PENDING);

                when(orderRepository.findById(anyLong()))
                                .thenReturn(Optional.of(order));

                ResponseEntity<?> responseEntity = orderService.cancelOrder(paymentDto);

                assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
                assertEquals("Order cannot be canceled as it is not waiting for payment", responseEntity.getBody());

                verify(orderRepository, times(1)).findById(anyLong());
                verifyNoMoreInteractions(orderRepository);
        }

        @Test
        public void testGetOrdersWaitingShipping() {
                Long userId = 123L;
                List<Order> orders = new ArrayList<>();
                Order order1 = new Order();
                order1.setId(1L);
                order1.setUserId(userId);
                order1.setStatus(OrderStatus.WAITING_SHIPPING);

                Order order2 = new Order();
                order2.setId(2L);
                order2.setUserId(userId);
                order2.setStatus(OrderStatus.WAITING_SHIPPING);

                orders.add(order1);
                orders.add(order2);

                when(orderRepository.findAllByUserIdAndStatus(userId, OrderStatus.WAITING_SHIPPING))
                                .thenReturn(orders);


                List<Order> result = orderService.getOrdersWaitingShipping(userId);

                assertEquals(2, result.size());
                assertEquals(OrderStatus.WAITING_SHIPPING, result.get(0).getStatus());
                assertEquals(OrderStatus.WAITING_SHIPPING, result.get(1).getStatus());
        }

        @Test
        public void testGetOrdersWaitingPayment() {
                Long userId = 123L;
                List<Order> orders = new ArrayList<>();
                Order order1 = new Order();
                order1.setId(3L);
                order1.setUserId(userId);
                order1.setStatus(OrderStatus.WAITING_PAYMENT);

                Order order2 = new Order();
                order2.setId(4L);
                order2.setUserId(userId);
                order2.setStatus(OrderStatus.WAITING_PAYMENT);

                orders.add(order1);
                orders.add(order2);

                when(orderRepository.findAllByUserIdAndStatus(userId, OrderStatus.WAITING_PAYMENT))
                                .thenReturn(orders);


                List<Order> result = orderService.getOrdersWaitingPayment(userId);

                assertEquals(2, result.size());
                assertEquals(OrderStatus.WAITING_PAYMENT, result.get(0).getStatus());
                assertEquals(OrderStatus.WAITING_PAYMENT, result.get(1).getStatus());
        }

}
