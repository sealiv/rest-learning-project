package org.aleks4ay.api;

import org.aleks4ay.dto.Event;

import java.util.Collection;

public interface EventService {
    Event createEvent(Event event);
    void updateEvent(Event event);
    Event getEvent(long id);
    void deleteEvent(long id);
    Collection<Event> getAllEvents();
    Collection<Event> getAllEventsByTitle(String title);
}
