package com.rakbank.service;


import com.rakbank.entity.Notification;
import com.rakbank.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    @KafkaListener(id= "book", topics = "bookingNotifications")
    public void handleBookingNotification(Notification notification) {
        sendBookingNotification(notification);
    }

    @KafkaListener(id = "cancel", topics = "cancellationNotifications")
    public void handleCancellationNotification(Notification notification) {
        sendCancellationNotification(notification);
    }

    public Notification sendBookingNotification(Notification notification) {
        // Set notification details
        notification.setStatus("SENT");
        notification.setSentAt(LocalDateTime.now());

        // Log or print notification as a simulation of sending
        System.out.println("Notification sent to " + notification.getUserName() +
                ": You have successfully booked tickets for " + notification.getEventName());

        // Save to database (optional)
        return notificationRepository.save(notification);
    }

    public Notification sendCancellationNotification(Notification notification) {
        notification.setStatus("CANCELLED");
        notification.setSentAt(LocalDateTime.now());

        System.out.println("Notification sent to " + notification.getUserName() +
                ": Your booking for " + notification.getEventName() + " has been cancelled.");

        return notificationRepository.save(notification);
    }
}
