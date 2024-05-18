package id.ac.ui.cs.advprog.functionality.dto;


import lombok.Data;

@Data
public class AddBookCartDto {
    private Long userId;
    private int bookId;
}