package com.rakbank.repository;

import com.rakbank.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByLocation(String location);
    List<Event> findByDate(LocalDate date);
    List<Event> findByNameContaining(String name);

}
