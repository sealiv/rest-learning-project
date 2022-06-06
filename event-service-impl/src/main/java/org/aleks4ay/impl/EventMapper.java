package org.aleks4ay.impl;

import org.aleks4ay.dto.Event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EventMapper {

    public Event extractFromResultSet(ResultSet rs) throws SQLException {
        Event event = new Event();
        event.setId(rs.getLong("id"));
        event.setTitle(rs.getString("title"));
        event.setPlace(rs.getString("place"));
        event.setSpeaker(rs.getString("speaker"));
        event.setEventType(rs.getString("event_type"));
        event.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
        return event;
    }

    public void insertToResultSet(PreparedStatement statement, Event event) throws SQLException {
        statement.setString(1, event.getTitle());
        statement.setString(2, event.getPlace());
        statement.setString(3, event.getSpeaker());
        statement.setString(4, event.getEventType());
        statement.setTimestamp(5, Timestamp.valueOf(event.getDateTime()));
        if (event.getId() != 0L) {
            statement.setLong(6, event.getId());
        }
    }
}
