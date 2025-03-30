package com.example.demo.service.implimentation;

import com.example.demo.exception.AppNotFoundException;
import com.example.demo.model.dto.request.EventRequest;
import com.example.demo.model.entity.Attendee;
import com.example.demo.model.entity.Event;
import com.example.demo.model.entity.Venue;
import com.example.demo.repository.AttendeeRepository;
import com.example.demo.repository.EventAttendeeRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.VenueRepository;
import com.example.demo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImp implements EventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final AttendeeRepository attendeeRepository;
    private final EventAttendeeRepository eventAttendeeRepository;

    @Override
    public List<Event> getAllEvents(Integer offset, Integer limit) {
        return eventRepository.getAllEvents(offset, limit);
    }

    @Override
    public Event findEventById(Integer eventId) {
        Event event = eventRepository.findEventById(eventId);
        if (event == null) {
            throw new AppNotFoundException("Event with ID " + eventId + " not found!");
        }
        return event;
    }

    @Override
    public Event insertEvent(EventRequest eventRequest) {

        Venue venue = venueRepository.findVenueById(eventRequest.getVenueId());
        if (venue == null) {
            throw new AppNotFoundException("Venue with ID " + eventRequest.getVenueId() + " not found!");
        }

        Integer notfoundId = null;
        for (Integer attendeeId : eventRequest.getAttendeesId()) {
            Attendee attendee = attendeeRepository.findAttendeeById(attendeeId);
            if (attendee == null) {
                notfoundId = attendeeId;
                break;
            }
        }
        if (notfoundId != null) {
            throw new AppNotFoundException("Attendee with ID " + notfoundId + " not found!");
        }

        Event event = eventRepository.insertEvent(eventRequest);

        for (Integer attendeeId : eventRequest.getAttendeesId()) {
            eventAttendeeRepository.insertEventAttendee(event.getEventId(), attendeeId);
        }
        return this.findEventById(event.getEventId());
    }

    @Override
    public Event updateEvent(Integer eventId, EventRequest eventRequest) {
        return null;
    }

    @Override
    public Event deleteEventById(Integer eventId) {
        Event event = eventRepository.deleteEventById(eventId);
        if (event == null) {
            throw new AppNotFoundException("Event with ID " + eventId + " not found!");
        }
        return event;
    }
}
