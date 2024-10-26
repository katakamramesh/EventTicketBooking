package com.rakbank.service;

import com.rakbank.entity.Payment;
import com.rakbank.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(Long bookingId, double amount) {
        // Basic validation: Check for positive payment amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid payment amount.");
        }

        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setAmount(amount);
        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentDate(LocalDateTime.now());

        // Save the payment record
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public List<Payment> getPaymentsByBookingId(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    public Payment refundPayment(Long paymentId) {
        Payment payment = getPaymentById(paymentId);

        // Check if the payment is already refunded
        if ("REFUNDED".equals(payment.getPaymentStatus())) {
            throw new RuntimeException("Payment has already been refunded.");
        }

        payment.setPaymentStatus("REFUNDED");
        return paymentRepository.save(payment);
    }
}
