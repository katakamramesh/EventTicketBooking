package com.rakbank.repository;

import com.rakbank.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByEventId(Long eventId);
    List<Booking> findByUserName(String userName);
}
