package com.example.demo.controller;

import com.example.demo.model.dto.request.VenueRequest;
import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.model.entity.Venue;
import com.example.demo.service.VenueService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/venues")
public class VenueController {
    private final VenueService venueService;

    @GetMapping
    ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(
        @RequestParam(defaultValue = "1") @Positive @Min(value = 1, message = "Offset must be greater than 0") Integer offset,
        @RequestParam(defaultValue = "10") @Positive @Min(value = 1, message = "Limit must be greater than 0") Integer limit
    ) {

        List<Venue> venues = venueService.getAllVenues(offset, limit);
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .message("Get all venues successfully!")
                .payload(venues)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{venue-id}")
    ResponseEntity<ApiResponse<Venue>> findVenueById(@PathVariable("venue-id") Integer venueId) {
        Venue venue = venueService.findVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Venue has been found successfully!")
                .payload(venue)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<ApiResponse<Venue>> insertVenue(@RequestBody @Valid VenueRequest venueRequest) {
        Venue venue = venueService.insertVenue(venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Venue has been created successfully!")
                .payload(venue)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{venue-id}")
    ResponseEntity<ApiResponse<Venue>> updateVenue(@PathVariable("venue-id") Integer venueId, @RequestBody @Valid VenueRequest venueRequest) {
        Venue venue = venueService.updateVenue(venueId, venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Venue has been updated successfully!")
                .payload(venue)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{venue-id}")
    ResponseEntity<ApiResponse<Venue>> deleteVenueById(@PathVariable("venue-id") Integer venueId) {
        Venue venue = venueService.deleteVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Venue ID " + venueId + " has been deleted successfully!")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }
}