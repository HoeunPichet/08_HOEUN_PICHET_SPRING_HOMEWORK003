package com.example.demo.service.implimentation;

import com.example.demo.exception.AppNotFoundException;
import com.example.demo.model.dto.request.VenueRequest;
import com.example.demo.model.entity.Venue;
import com.example.demo.repository.VenueRepository;
import com.example.demo.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImp implements VenueService {
    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(Integer offset, Integer limit) {
        return venueRepository.getAllVenues(offset, limit);
    }

    @Override
    public Venue findVenueById(Integer venueId) {
        Venue venue = venueRepository.findVenueById(venueId);
        if (venue == null) {
            throw new AppNotFoundException("Venue with ID " + venueId + " not found!");
        }

        return venue;
    }

    @Override
    public Venue insertVenue(VenueRequest venueRequest) {
        return venueRepository.insertVenue(venueRequest);
    }

    @Override
    public Venue updateVenue(Integer venueId, VenueRequest venueRequest) {
        Venue venue = venueRepository.updateVenue(venueId, venueRequest);
        if (venue == null) {
            throw new AppNotFoundException("Venue with ID " + venueId + " not found!");
        }

        return venue;
    }

    @Override
    public Venue deleteVenueById(Integer venueId) {
        Venue venue = venueRepository.deleteVenueById(venueId);
        if (venue == null) {
            throw new AppNotFoundException("Venue with ID " + venueId + " not found!");
        }
        return venue;
    }
}
