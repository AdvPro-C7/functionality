package id.ac.ui.cs.advprog.functionality.dto;
import lombok.Data;

@Data
public class CartItemsDto {
    private Long id;

    private Long price;

    private Long quantity;

    private Long bookId;

    private Long orderId;

    private String bookName;

    private String returnedImg;

    private Long userId;

}
