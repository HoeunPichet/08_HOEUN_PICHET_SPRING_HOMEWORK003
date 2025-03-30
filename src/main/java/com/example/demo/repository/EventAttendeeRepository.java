package com.example.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EventAttendeeRepository {

    @Insert("""
        INSERT INTO event_attendee(event_id, attendee_id)
        VALUES(#{eventId}, #{attendeeId})
    """)
    void insertEventAttendee(Integer eventId, Integer attendeeId);


    @Select("""
        DELETE FROM event_attendee
        WHERE event_id = #{eventId}
    """)
    void deleteEventAttendee(Integer eventId);
}