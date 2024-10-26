package com.rakbank.controller;

import com.rakbank.entity.Payment;
import com.rakbank.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestParam Long bookingId, @RequestParam double amount) {
        Payment payment = paymentService.processPayment(bookingId, amount);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping("/booking/{id}")
    public List<Payment> getPaymentsByBookingId(@PathVariable Long id) {
        return paymentService.getPaymentsByBookingId(id);
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<Payment> refundPayment(@PathVariable Long id) {
        Payment refundedPayment = paymentService.refundPayment(id);
        return new ResponseEntity<>(refundedPayment, HttpStatus.OK);
    }
}
