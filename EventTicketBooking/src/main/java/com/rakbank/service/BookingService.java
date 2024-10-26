package com.rakbank.service;

import com.rakbank.entity.Booking;
import com.rakbank.entity.Event;
import com.rakbank.entity.Payment;
import com.rakbank.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private RestTemplate restTemplate; // Autowired RestTemplate for inter-service calls

    private static final String EVENT_SERVICE_URL = "http://localhost:8081/events"; // Assuming EventService runs on port 8081

    public Booking createBooking(Booking booking) {
        // Process payment through PaymentService
        ResponseEntity<Payment> paymentResponse = restTemplate.postForEntity(
                "http://localhost:8082/payments?bookingId=" + booking.getId() + "&amount=" + booking.getPaymentAmount(),
                null,
                Payment.class
        );

        if (!"SUCCESS".equals(paymentResponse.getBody().getPaymentStatus())) {
            throw new RuntimeException("Payment failed.");
        }

        // Save booking if payment succeeds
        return bookingRepository.save(booking);
    }


    private boolean isTicketsAvailable(Long eventId, int requestedTickets) {
        // Fetch event details from EventService
        Event event = restTemplate.getForObject(EVENT_SERVICE_URL + "/" + eventId, Event.class);

        // Check if requested tickets are available
        return event != null && event.getAvailableTickets() >= requestedTickets;
    }

    private void processPayment(Booking booking) {
        if (booking.getPaymentAmount() <= 0) {
            throw new RuntimeException("Invalid payment amount.");
        }
        // Simulate a successful payment processing step
        // In a real-world scenario, integrate with a payment gateway here
    }

    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getBookingsByUserName(String userName) {
        return bookingRepository.findByUserName(userName);
    }

    public List<Booking> getBookingsByEventId(Long eventId) {
        return bookingRepository.findByEventId(eventId);
    }
}
