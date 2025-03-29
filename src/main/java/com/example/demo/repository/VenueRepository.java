package com.example.demo.repository;

import com.example.demo.model.dto.request.VenueRequest;
import com.example.demo.model.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Select("""
        SELECT * FROM venues
        ORDER BY venue_id
        OFFSET #{limit} * (#{offset} - 1) LIMIT #{limit}
    """)
    @Results(id = "venueMapper", value = {
        @Result(property = "venueId", column = "venue_id"),
        @Result(property = "venueName", column = "venue_name")
    })
    List<Venue> getAllVenues(Integer offset, Integer limit);


    @Select("""
        SELECT * FROM venues
        WHERE venue_id = #{venueId}
    """)
    @ResultMap("venueMapper")
    Venue findVenueById(Integer venueId);


    @Select("""
        INSERT INTO venues(venue_name, location)
        VALUES (#{venue.venueName}, #{venue.location})
        RETURNING *
    """)
    @ResultMap("venueMapper")
    Venue insertVenue(@Param("venue") VenueRequest venueRequest);


    @Select("""
        UPDATE venues
        SET venue_name = #{venue.venueName},
            location = #{venue.location}
        WHERE venue_id = #{venueId}
        RETURNING *
    """)
    @ResultMap("venueMapper")
    Venue updateVenue(Integer venueId, @Param("venue") VenueRequest venueRequest);


    @Select("""
        DELETE FROM venues
        WHERE venue_id = #{venueId}
        RETURNING *
    """)
    @ResultMap("venueMapper")
    Venue deleteVenueById(Integer venueId);
}
