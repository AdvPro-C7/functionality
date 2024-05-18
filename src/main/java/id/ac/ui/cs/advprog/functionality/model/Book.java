package id.ac.ui.cs.advprog.functionality.model;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter

@Entity
@Table(name = "book", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "author"})
})

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    int id;

    @Column(name = "title", updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    String title;

    @Column(name = "author", updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    String author;

    @Column(name = "publisher", nullable = false)
    String publisher;

    @Column(name = "price")
    double price;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "stock")
    int stock;

    @Column(name = "publish_date", nullable = false)
    String publishDate;

    @Column(name = "isbn", nullable = false)
    String isbn;

    @Column(name = "pages")
    int pages;

    @Column(name = "cover_picture", nullable = false)
    String coverPicture;

    @Column(name = "category", nullable = false)
    String category;

    @Column(name = "sold", nullable = false)
    int sold;



    public Book() {

    }

    public Book(int id, String title, String author, String publisher, double price, String description,
                int stock, String publishDate, String isbn, int pages, String coverPicture, String category, int sold){
   
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.publishDate = publishDate;
        this.isbn = isbn;
        this.pages = pages;
        this.coverPicture = coverPicture;
        this.category = category;
        this.sold = sold;
    }

    public void setSold(int sold) {
        if (sold < 0) {
            throw new IllegalArgumentException();
        } else {
            this.sold = sold;
        }
    }

    public void setStock(int stock) {
        if (stock <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.stock = stock;
        }
    }
}
