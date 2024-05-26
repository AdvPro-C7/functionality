package id.ac.ui.cs.advprog.functionality.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItems {
    @Id
    private int bookId;

    private int quantity;

    private String bookTitle;

    private String author;

    private double price;

    private String coverPicture;

    private Long userId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

}
