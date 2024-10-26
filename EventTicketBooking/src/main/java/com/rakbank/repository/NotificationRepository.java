package com.rakbank.repository;

import com.rakbank.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Custom query method to find notifications by user name
    List<Notification> findByUserName(String userName);

    // Custom query method to find notifications by status (e.g., SENT, FAILED, CANCELLED)
    List<Notification> findByStatus(String status);

    // Custom query method to find notifications by bookingId
    List<Notification> findByBookingId(Long bookingId);
}
