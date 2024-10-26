package com.rakbank.service;


import com.rakbank.entity.Booking;
import com.rakbank.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    private Booking sampleBooking;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleBooking = new Booking(1L, 1L, "John Doe", LocalDate.now(), 2, 100.0);
    }

    @Test
    void testGetBookingsByUserName() {
        when(bookingRepository.findByUserName("John Doe")).thenReturn(List.of(sampleBooking));
        List<Booking> bookings = bookingService.getBookingsByUserName("John Doe");
        assertEquals(1, bookings.size());
        assertEquals("John Doe", bookings.get(0).getUserName());
    }

    @Test
    void testCancelBooking() {
        bookingService.cancelBooking(1L);
        verify(bookingRepository, times(1)).deleteById(1L);
    }
}
