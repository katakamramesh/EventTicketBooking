package com.rakbank.controller;

import com.rakbank.entity.Booking;
import com.rakbank.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{name}")
    public List<Booking> getBookingsByUserName(@PathVariable String name) {
        return bookingService.getBookingsByUserName(name);
    }

    @GetMapping("/event/{id}")
    public List<Booking> getBookingsByEventId(@PathVariable Long id) {
        return bookingService.getBookingsByEventId(id);
    }
}
