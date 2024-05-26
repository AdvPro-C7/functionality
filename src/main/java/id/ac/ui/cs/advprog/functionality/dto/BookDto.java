package id.ac.ui.cs.advprog.functionality.dto;

import lombok.Data;

@Data
public class BookDto {
    private int id;
    private String title;
    private String author;
    private double price;
    private String coverPicture;
}