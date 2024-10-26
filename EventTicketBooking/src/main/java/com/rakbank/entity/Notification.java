package com.rakbank.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;
    private String userName;
    private String eventName;
    private LocalDateTime eventDate;
    private String eventLocation;
    private String ticketType;
    private int numberOfTickets;
    private double paymentAmount;
    private String status;
    private LocalDateTime sentAt;

    public Notification(Long bookingId, String userName, String eventName, LocalDateTime eventDate,
                        String eventLocation, String ticketType, int numberOfTickets, double paymentAmount,
                        String status, LocalDateTime sentAt) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.ticketType = ticketType;
        this.numberOfTickets = numberOfTickets;
        this.paymentAmount = paymentAmount;
        this.status = status;
        this.sentAt = sentAt;
    }
}
