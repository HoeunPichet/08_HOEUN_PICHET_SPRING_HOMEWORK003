package com.example.demo.model.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventRequest {
    private String eventName;
    private LocalDateTime eventDate;
    private Integer venueId;
    private List<Integer> attendeesId;
}
