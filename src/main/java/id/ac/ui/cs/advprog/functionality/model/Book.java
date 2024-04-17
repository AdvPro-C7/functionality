package id.ac.ui.cs.advprog.functionality.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Getter
    private Long id;

    @Column(name = "title")
    @Getter
    @Setter
    private String title;

    @Column(name = "author")
    @Getter
    @Setter
    private String author;

    @Column(name = "price")
    @Getter
    @Setter
    private int price;

    public Book() {
    }

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
