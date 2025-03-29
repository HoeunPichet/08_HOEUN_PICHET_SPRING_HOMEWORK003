package com.example.demo.service.implimentation;

import com.example.demo.exception.AleadyExistingException;
import com.example.demo.exception.AppNotFoundException;
import com.example.demo.model.dto.request.AttendeeRequest;
import com.example.demo.model.entity.Attendee;
import com.example.demo.repository.AttendeeRepository;
import com.example.demo.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendeeServiceImp implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Attendee> getAllAttendees(Integer offset, Integer limit) {
        return attendeeRepository.getAllAttendees(offset, limit);
    }

    @Override
    public Attendee findAttendeeById(Integer attendeeId) {
        Attendee attendee = attendeeRepository.findAttendeeById(attendeeId);
        if (attendee == null) {
            throw new AppNotFoundException("Attendee with ID " + attendeeId + " not found!");
        }

        return attendee;
    }

    @Override
    public Attendee insertAttendee(AttendeeRequest attendeeRequest) {
        Attendee email = attendeeRepository.checkedExistingEmail(attendeeRequest.getEmail());
        if (email != null) {
            throw new AleadyExistingException("Email has been taken!");
        }

        return attendeeRepository.insertAttendee(attendeeRequest);
    }

    @Override
    public Attendee updateAttendee(Integer attendeeId, AttendeeRequest attendeeRequest) {
        Attendee existingData = attendeeRepository.findAttendeeById(attendeeId);

        if (existingData == null) {
            throw new AppNotFoundException("Attendee with ID " + attendeeId + " not found!");
        }

        Attendee email = attendeeRepository.checkedExistingEmail(attendeeRequest.getEmail());
        if (email != null && !existingData.getEmail().equals(email.getEmail())) {
            throw new AleadyExistingException("Email has been taken!");
        }

        return attendeeRepository.updateAttendee(attendeeId, attendeeRequest);
    }

    @Override
    public Attendee deleteAttendeeById(Integer attendeeId) {
        Attendee attendee = attendeeRepository.deleteAttendeeById(attendeeId);
        if (attendee == null) {
            throw new AppNotFoundException("Attendee with ID " + attendeeId + " not found!");
        }
        return attendee;
    }
}
