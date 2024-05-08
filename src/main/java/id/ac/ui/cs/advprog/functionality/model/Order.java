package id.ac.ui.cs.advprog.functionality.model;


import id.ac.ui.cs.advprog.functionality.dto.OrderDto;
import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long totalPrice;

    private String shippingAddress;

    private OrderStatus status;


    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<CartItems> cartItems;

    public OrderDto getOrderDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setTotalPrice(totalPrice);
        orderDto.setShippingAddress(shippingAddress);
        orderDto.setStatus(status);
        orderDto.setUsername(user.getNama());
        orderDto.setOrderDate(orderDate);

        return orderDto;

    }
}
