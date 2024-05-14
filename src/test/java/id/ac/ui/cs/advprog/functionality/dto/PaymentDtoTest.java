package id.ac.ui.cs.advprog.functionality.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentDtoTest {

    @Test
    public void testPaymentDto() {
        // Create sample data
        Long userId = 1L;
        Long orderId = 10L;

        // Create a PaymentDto object
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUserId(userId);
        paymentDto.setOrderId(orderId);

        // Test getters
        assertEquals(userId, paymentDto.getUserId());
        assertEquals(orderId, paymentDto.getOrderId());
    }
}