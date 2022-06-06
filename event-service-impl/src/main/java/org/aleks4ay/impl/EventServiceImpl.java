package org.aleks4ay.impl;

import org.aleks4ay.api.EventService;
import org.aleks4ay.dto.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventServiceImpl implements EventService {

    private final Connection connection = new ConnectH2().getConnection();
    private static final Logger log = LoggerFactory.getLogger(ConnectH2.class);
    private final EventMapper eventMapper = new EventMapper();

    private static final String GET = "SELECT * FROM event WHERE id=?;";
    private static final String GET_ALL_BY_TITLE = "SELECT * FROM event WHERE title=?;";
    private static final String GET_ALL = "SELECT * FROM event ORDER BY 'id';";
    private static final String DELETE = "DELETE FROM event WHERE id=?;";
    private static final String CREATE = "INSERT INTO event (title, place, speaker, event_type, date_time) VALUES (?,?,?,?,?);";
    private static final String UPDATE = "UPDATE event SET title=?, place=?, speaker=?, event_type=?, date_time=? WHERE id=?;";

    @Override
    public Event createEvent(Event event) {
        try (PreparedStatement prepStatement = connection.prepareStatement(CREATE, new String[]{"id"})) {
            eventMapper.insertToResultSet(prepStatement, event);
            prepStatement.executeUpdate();

            ResultSet rs = prepStatement.getGeneratedKeys();
            if (rs.next()) {
                event.setId(rs.getLong(1));
                return event;
            }
        } catch (SQLException e) {
            log.warn("Exception during insert nev Event to DB.", e);
        }
        return null;
    }

    @Override
    public void updateEvent(Event event) {
        try (PreparedStatement prepStatement = connection.prepareStatement(UPDATE)) {
            eventMapper.insertToResultSet(prepStatement, event);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            log.warn("Exception during insert nev Event to DB.", e);
        }
    }

    @Override
    public Event getEvent(long id) {
        Event event = null;
        try (PreparedStatement prepStatement = connection.prepareStatement(GET)){
            prepStatement.setLong(1, id);
            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {
                event = eventMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            log.warn("Exception during getting Event with id = '{}'.", id, e);
        }
        return event;
    }

    @Override
    public void deleteEvent(long id) {
        try (PreparedStatement prepStatement = connection.prepareStatement(DELETE)){
            prepStatement.setLong(1, id);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            log.warn("Exception during deleting Event with id = '{}'.", id, e);
        }
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (PreparedStatement prepStatement = connection.prepareStatement(GET_ALL)){
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                events.add(eventMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            log.warn("Exception during getting all Event.", e);
        }
        return events;
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        List<Event> events = new ArrayList<>();
        try (PreparedStatement prepStatement = connection.prepareStatement(GET_ALL_BY_TITLE)){
            prepStatement.setString(1, title);
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                events.add(eventMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            log.warn("Exception during getting all Event.", e);
        }
        return events;
    }
}
