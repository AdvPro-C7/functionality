package id.ac.ui.cs.advprog.functionality.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentDtoTest {

    @Test
    public void testPaymentDto() {
        // Create sample data
        Long userId = 1L;
        Long orderId = 10L;

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUserId(userId);
        paymentDto.setOrderId(orderId);

        assertEquals(userId, paymentDto.getUserId());
        assertEquals(orderId, paymentDto.getOrderId());
    }
}