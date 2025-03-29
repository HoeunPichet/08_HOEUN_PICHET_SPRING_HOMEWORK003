package com.example.demo.service.implimentation;

import com.example.demo.model.dto.request.EventRequest;
import com.example.demo.model.entity.Event;
import com.example.demo.service.EventService;

import java.util.List;

public class EventServiceImp implements EventService {
    @Override
    public List<Event> getAllEvents(Long offset, Long limit) {
        return List.of();
    }

    @Override
    public Event findEventById(Long id) {
        return null;
    }

    @Override
    public Event insertEvent(EventRequest eventRequest) {
        return null;
    }

    @Override
    public Event updateEvent(Long eventId, EventRequest eventRequest) {
        return null;
    }

    @Override
    public Event deleteEventById(Long id) {
        return null;
    }
}
