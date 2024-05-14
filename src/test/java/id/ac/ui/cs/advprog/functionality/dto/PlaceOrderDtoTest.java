package id.ac.ui.cs.advprog.functionality.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceOrderDtoTest {

    @Test
    public void testPlaceOrderDto() {
        // Create sample data
        Long userId = 1L;
        String address = "123 Main St, City";

        // Create a PlaceOrderDto object
        PlaceOrderDto placeOrderDto = new PlaceOrderDto();
        placeOrderDto.setUserId(userId);
        placeOrderDto.setAddress(address);

        // Test getters
        assertEquals(userId, placeOrderDto.getUserId());
        assertEquals(address, placeOrderDto.getAddress());
    }
}

