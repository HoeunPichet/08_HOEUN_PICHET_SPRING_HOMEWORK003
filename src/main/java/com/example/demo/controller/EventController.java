package com.example.demo.controller;

import com.example.demo.model.dto.request.EventRequest;
import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.model.entity.Event;
import com.example.demo.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam(required = false, defaultValue = "1") Long offset, @RequestParam(required = false, defaultValue = "10") Long limit) {
        List<Event> events = eventService.getAllEvents(offset, limit);

        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .message("Get all events successfully!")
                .payload(events)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{event-id}")
    ResponseEntity<ApiResponse<Event>> findEventById(@PathVariable("event-id") Long eventId) {
        Event event = eventService.findEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Event has been found successfully!")
                .payload(event)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<ApiResponse<Event>> insertEvent(@RequestBody @Valid EventRequest eventRequest) {
        Event event = eventService.insertEvent(eventRequest);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Event has been created successfully!")
                .payload(event)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{event-id}")
    ResponseEntity<ApiResponse<Event>> updateEvent(@PathVariable("event-id") Long eventId, @RequestBody @Valid EventRequest eventRequest) {
        Event event = eventService.updateEvent(eventId, eventRequest);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Event has been updated successfully!")
                .payload(event)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{event-id}")
    ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable("event-id") Long eventId) {
        Event event = eventService.deleteEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Event ID " + eventId + " has been deleted successfully!")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }
}
