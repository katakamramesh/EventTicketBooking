package com.rakbank.controller;

import com.rakbank.entity.Notification;
import com.rakbank.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/booking")
    public ResponseEntity<Notification> sendBookingNotification(@RequestBody Notification notification) {
        Notification sentNotification = notificationService.sendBookingNotification(notification);
        return new ResponseEntity<>(sentNotification, HttpStatus.OK);
    }

    @PostMapping("/cancellation")
    public ResponseEntity<Notification> sendCancellationNotification(@RequestBody Notification notification) {
        Notification cancelledNotification = notificationService.sendCancellationNotification(notification);
        return new ResponseEntity<>(cancelledNotification, HttpStatus.OK);
    }
}
