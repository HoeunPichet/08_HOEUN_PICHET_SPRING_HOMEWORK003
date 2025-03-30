package com.example.demo.repository;

import com.example.demo.model.dto.request.AttendeeRequest;
import com.example.demo.model.entity.Attendee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Select("""
        SELECT * FROM attendees
        ORDER BY attendee_id
        OFFSET #{limit} * (#{offset} - 1) LIMIT #{limit}
    """)
    @Results(id = "attendeeMapper", value = {
        @Result(property = "attendeeId", column = "attendee_id"),
        @Result(property = "attendeeName", column = "attendee_name")
    })
    List<Attendee> getAllAttendees(Integer offset, Integer limit);


    @Select("""
        SELECT * FROM attendees
        WHERE attendee_id = #{attendeeId}
    """)
    @ResultMap("attendeeMapper")
    Attendee findAttendeeById(Integer attendeeId);


    @Select("""
        SELECT a.* FROM event_attendee ea
        LEFT JOIN attendees a ON ea.attendee_id = a.attendee_id
        WHERE event_id = #{eventId}
        ORDER BY a.attendee_id
    """)
    @ResultMap("attendeeMapper")
    List<Attendee> findAttendeesByEventId(Integer eventId);


    @Select("""
        SELECT email FROM attendees
        WHERE email = #{email}
    """)
    @ResultMap("attendeeMapper")
    Attendee checkedExistingEmail(String email);


    @Select("""
        INSERT INTO attendees(attendee_name, email)
        VALUES (#{attendee.attendeeName}, #{attendee.email})
        RETURNING *
    """)
    @ResultMap("attendeeMapper")
    Attendee insertAttendee(@Param("attendee") AttendeeRequest attendeeRequest);


    @Select("""
        UPDATE attendees
        SET attendee_name = #{attendee.attendeeName},
            email = #{attendee.email}
        WHERE attendee_id = #{attendeeId}
        RETURNING *
    """)
    @ResultMap("attendeeMapper")
    Attendee updateAttendee(Integer attendeeId, @Param("attendee") AttendeeRequest attendeeRequest);


    @Select("""
        DELETE FROM attendees
        WHERE attendee_id = #{attendeeId}
        RETURNING *
    """)
    @ResultMap("attendeeMapper")
    Attendee deleteAttendeeById(Integer attendeeId);
}
