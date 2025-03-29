package com.example.demo.service;

import com.example.demo.model.dto.request.AttendeeRequest;
import com.example.demo.model.entity.Attendee;
import jakarta.validation.Valid;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees(Integer offset, Integer limit);
    Attendee findAttendeeById(Integer attendeeId);
    Attendee insertAttendee(@Valid AttendeeRequest attendeeRequest);
    Attendee updateAttendee(Integer attendeeId, @Valid AttendeeRequest attendeeRequest);
    Attendee deleteAttendeeById(Integer attendeeId);
}
