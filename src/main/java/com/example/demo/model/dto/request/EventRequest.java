package com.example.demo.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventRequest {
    @NotBlank(message = "Event Name cannot be blank")
    private String eventName;

    @NotNull(message = "Event Date cannot be null")
    private LocalDateTime eventDate = LocalDateTime.now();

    @NotNull(message = "Venue ID cannot be null")
    private Integer venueId;

    @NotEmpty(message = "Attendees ID cannot be empty")
    @Size(min = 1, message = "At least one attendee ID must be provided")
    @Valid
    private List<@NotNull(message = "Attendee ID must be null") Integer> attendeesId;
}
