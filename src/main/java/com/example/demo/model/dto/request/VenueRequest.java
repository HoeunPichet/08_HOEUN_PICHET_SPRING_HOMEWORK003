package com.example.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VenueRequest {
    @NotBlank(message = "Venue Name cannot be blank")
    private String venueName;

    @NotBlank(message = "Location cannot be blank")
    private String location;
}
