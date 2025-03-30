package com.example.demo.service;

import com.example.demo.model.dto.request.EventRequest;
import com.example.demo.model.entity.Event;
import jakarta.validation.Valid;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer offset, Integer limit);
    Event findEventById(Integer eventId);
    Event insertEvent(@Valid EventRequest eventRequest);
    Event updateEvent(Integer eventId, @Valid EventRequest eventRequest);
    Event deleteEventById(Integer eventId);
}
