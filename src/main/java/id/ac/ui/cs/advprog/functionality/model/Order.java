package id.ac.ui.cs.advprog.functionality.model;


import id.ac.ui.cs.advprog.functionality.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalPrice;

    private String shippingAddress;

    private OrderStatus status;


    private Date orderDate;

    private Long userId;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<CartItems> cartItems;

}
