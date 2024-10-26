package com.rakbank.service;

import com.rakbank.entity.Notification;
import com.rakbank.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private Notification sampleNotification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleNotification = new Notification(1L, "John Doe", "Concert", LocalDateTime.now().plusDays(5),
                "Stadium", "VIP", 2, 100.0, "SENT", LocalDateTime.now());
    }

    @Test
    void testSendBookingNotification() {
        when(notificationRepository.save(sampleNotification)).thenReturn(sampleNotification);
        Notification sentNotification = notificationService.sendBookingNotification(sampleNotification);
        assertEquals("SENT", sentNotification.getStatus());
    }

    @Test
    void testSendCancellationNotification() {
        when(notificationRepository.save(sampleNotification)).thenReturn(sampleNotification);
        sampleNotification.setStatus("CANCELLED");
        Notification cancelledNotification = notificationService.sendCancellationNotification(sampleNotification);
        assertEquals("CANCELLED", cancelledNotification.getStatus());
    }
}
