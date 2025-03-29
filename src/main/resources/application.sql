CREATE DATABASE kshrd_center;

CREATE TABLE attendees(
    attendee_id SERIAL PRIMARY KEY,
    attendee_name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE venues(
    venue_id SERIAL PRIMARY KEY,
    venue_name VARCHAR(100),
    location VARCHAR(250)
);

CREATE TABLE events(
    event_id SERIAL PRIMARY KEY,
    event_name VARCHAR(250),
    event_date TIMESTAMP DEFAULT now(),
    venue_id INT,
    FOREIGN KEY (venue_id) REFERENCES venues(venue_id) ON DELETE CASCADE
);

CREATE TABLE event_attendee(
    id SERIAL PRIMARY KEY,
    event_id INT,
    attendee_id INT,
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE,
    FOREIGN KEY (attendee_id) REFERENCES attendees(attendee_id) ON DELETE CASCADE
);