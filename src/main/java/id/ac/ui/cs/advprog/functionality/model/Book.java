package id.ac.ui.cs.advprog.functionality.model;

import lombok.Getter;

@Getter

public class Book {
    String id;
    String title;
    String author;
    String publisher;
    double price;
    String description;
    int stock;
    String publishDate;
    String isbn;
    int pages;
    String coverPicture;
    String category;
    int sold;

    public Book(String id, String title, String author, String publisher, double price, String description,
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

    public void setStock(int stock){
        if (stock <= 0){
            throw new IllegalArgumentException();
        } else {
            this.stock = stock;
        }
    }

}
