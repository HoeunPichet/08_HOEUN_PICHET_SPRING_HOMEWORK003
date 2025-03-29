package com.example.demo.service;

import com.example.demo.model.dto.request.EventRequest;
import com.example.demo.model.entity.Event;
import jakarta.validation.Valid;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Long offset, Long limit);
    Event findEventById(Long id);
    Event insertEvent(@Valid EventRequest eventRequest);
    Event updateEvent(Long eventId, @Valid EventRequest eventRequest);
    Event deleteEventById(Long id);
}
