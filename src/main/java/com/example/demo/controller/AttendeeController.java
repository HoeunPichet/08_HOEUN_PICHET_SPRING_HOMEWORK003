package com.example.demo.controller;

import com.example.demo.model.dto.request.AttendeeRequest;
import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.model.entity.Attendee;
import com.example.demo.service.AttendeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping
    ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "Offset must be greater than 0") Integer offset,
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Limit must be greater than 0") Integer limit
    ) {

        List<Attendee> attendees = attendeeService.getAllAttendees(offset, limit);
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .message("Get all attendees successfully!")
                .payload(attendees)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{attendee-id}")
    ResponseEntity<ApiResponse<Attendee>> findAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
        Attendee attendee = attendeeService.findAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Attendee has been found successfully!")
                .payload(attendee)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<ApiResponse<Attendee>> insertAttendee(@RequestBody @Valid AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeService.insertAttendee(attendeeRequest);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Attendee has been created successfully!")
                .payload(attendee)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{attendee-id}")
    ResponseEntity<ApiResponse<Attendee>> updateAttendee(@PathVariable("attendee-id") Integer attendeeId, @RequestBody @Valid AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeService.updateAttendee(attendeeId, attendeeRequest);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Attendee has been updated successfully!")
                .payload(attendee)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{attendee-id}")
    ResponseEntity<ApiResponse<Attendee>> deleteAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
        Attendee attendee = attendeeService.deleteAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Attendee ID " + attendeeId + " has been deleted successfully!")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }
}
