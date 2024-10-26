package com.rakbank.service;

import com.rakbank.ResourceNotFoundException;
import com.rakbank.entity.Event;
import com.rakbank.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    private Event sampleEvent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleEvent = new Event(1L, "Concert", "Stadium", LocalDateTime.now().plusDays(5),  100);
    }

    @Test
    void testCreateEvent() {
        when(eventRepository.save(sampleEvent)).thenReturn(sampleEvent);
        Event createdEvent = eventService.createEvent(sampleEvent);
        assertEquals("Concert", createdEvent.getName());
        verify(eventRepository, times(1)).save(sampleEvent);
    }

    @Test
    void testGetEventById() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(sampleEvent));
        Event event = eventService.getEventById(1L).orElse(null);
        assertNotNull(event);
        assertEquals("Concert", event.getName());
        verify(eventRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateEvent() throws ResourceNotFoundException {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(sampleEvent));
        sampleEvent.setName("Updated Concert");
        when(eventRepository.save(sampleEvent)).thenReturn(sampleEvent);
        Event updatedEvent = eventService.updateEvent(1L, sampleEvent);
        assertEquals("Updated Concert", updatedEvent.getName());
    }

    @Test
    void testDeleteEvent() {
        eventService.deleteEvent(1L);
        verify(eventRepository, times(1)).deleteById(1L);
    }
}
