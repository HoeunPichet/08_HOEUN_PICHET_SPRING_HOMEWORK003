package com.example.demo.service;

import com.example.demo.model.dto.request.VenueRequest;
import com.example.demo.model.entity.Venue;
import jakarta.validation.Valid;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(Integer offset, Integer limit);
    Venue findVenueById(Integer venueId);
    Venue insertVenue(@Valid VenueRequest venueRequest);
    Venue updateVenue(Integer venueId, @Valid VenueRequest venueRequest);
    Venue deleteVenueById(Integer venueId);
}
