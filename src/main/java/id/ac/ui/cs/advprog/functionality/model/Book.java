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
    }

    public void setSold(int sold) {
    }

    public void setStock(int stock){
    }

}
