package id.ac.ui.cs.advprog.functionality.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Getter
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "book_id")
    @Getter
    @Setter
    private Book book;
    
    @Column(name = "quantity")
    @Getter
    @Setter
    private Integer quantity;


    public CartItem() {
        this.quantity = 0;
    }

    public CartItem(Book book,Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }

    
}
