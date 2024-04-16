package id.ac.ui.cs.advprog.functionality.model;
import org.checkerframework.checker.units.qual.C;

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
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "book_id")
    @Getter
    @Setter
    private Book book;
    
    @Column(name = "quantity")
    @Getter
    @Setter
    private int quantity;
    
    
}
