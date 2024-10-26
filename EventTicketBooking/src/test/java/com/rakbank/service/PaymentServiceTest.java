package com.rakbank.service;

import com.rakbank.entity.Payment;
import com.rakbank.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    private Payment samplePayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        samplePayment = new Payment(1L, 1L, 100.0, LocalDateTime.now(), "SUCCESS");
    }

    @Test
    void testProcessPayment() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(samplePayment);
        Payment processedPayment = paymentService.processPayment(1L, 100.0);
        assertEquals("SUCCESS", processedPayment.getPaymentStatus());
    }

    @Test
    void testRefundPayment() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(samplePayment));
        samplePayment.setPaymentStatus("NOT REFUNDED");
        when(paymentRepository.save(samplePayment)).thenReturn(samplePayment);
        Payment refundedPayment = paymentService.refundPayment(1L);
        assertEquals("REFUNDED", refundedPayment.getPaymentStatus());
    }
}
