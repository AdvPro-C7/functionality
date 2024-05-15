package id.ac.ui.cs.advprog.functionality.dto;


import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import id.ac.ui.cs.advprog.functionality.model.CartItems;
import id.ac.ui.cs.advprog.functionality.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Long id;

    private double totalPrice;

    private String shippingAddress;

    private OrderStatus status;


    private String username;

    private Date orderDate;

    private List<CartItemsDto> cartItems;
}
