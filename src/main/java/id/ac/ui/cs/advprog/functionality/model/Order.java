package id.ac.ui.cs.advprog.functionality.model;


import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

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

    @OneToOne(cascade =  CascadeType.MERGE)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<CartItem> items;

}
