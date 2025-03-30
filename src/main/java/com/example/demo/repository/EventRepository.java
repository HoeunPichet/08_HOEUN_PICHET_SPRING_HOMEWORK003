package com.example.demo.repository;

import com.example.demo.model.dto.request.EventRequest;
import com.example.demo.model.entity.Attendee;
import com.example.demo.model.entity.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("""
        SELECT * FROM events
        ORDER BY event_id
        OFFSET #{limit} * (#{offset} - 1) LIMIT #{limit}
    """)
    @Results(id = "eventMapper", value = {
        @Result(property = "eventId", column = "event_id"),
        @Result(property = "eventName", column = "event_name"),
        @Result(property = "eventDate", column = "event_date"),
        @Result(property = "venue", column = "venue_id",
            one = @One(select = "com.example.demo.repository.VenueRepository.findVenueById")
        ),
        @Result(property = "attendees", column = "event_id",
            many = @Many(select = "com.example.demo.repository.AttendeeRepository.findAttendeesByEventId")
        )
    })
    List<Event> getAllEvents(Integer offset, Integer limit);


    @Select("""
        SELECT * FROM events
        WHERE event_id = #{eventId}
    """)
    @ResultMap("eventMapper")
    Event findEventById(Integer eventId);


    @Select("""
        INSERT INTO events(event_name, event_date, venue_id)
        VALUES(#{event.eventName}, #{event.eventDate}, #{event.venueId})
        RETURNING event_id
    """)
    @ResultMap("eventMapper")
    Event insertEvent(@Param("event") EventRequest eventRequest);


    @Select("""
        UPDATE events
        SET event_name = #{event.eventName},
            event_date = #{event.eventDate},
            venue_id = #{event.venueId}
        WHERE event_id = #{eventId}
        RETURNING *
    """)
    @ResultMap("eventMapper")
    Event updateEvent(Integer eventId, @Param("event") EventRequest eventRequest);


    @Select("""
        DELETE FROM events
        WHERE event_id = #{eventId}
        RETURNING event_id
    """)
    @ResultMap("eventMapper")
    Event deleteEventById(Integer eventId);

}
