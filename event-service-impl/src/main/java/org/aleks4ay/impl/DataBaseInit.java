package org.aleks4ay.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseInit {

    private final String CREATE_DB = "create sequence event_id_seq START WITH 1;" +
            "CREATE TABLE event" +
            "( " +
            "    id bigint NOT NULL DEFAULT nextval('event_id_seq')," +
            "    title VARCHAR(255)," +
            "    place VARCHAR(255)," +
            "    speaker VARCHAR(255)," +
            "    event_type VARCHAR(255)," +
            "    date_time TIMESTAMP WITHOUT TIME ZONE," +
            "    PRIMARY KEY (id)" +
            ");";

    private final String INSERT = "INSERT INTO event (title, place, speaker, event_type, date_time) " +
            "VALUES ('title', 'place', 'speaker', 'event_type', '2022-06-02T12:00:01');";


    public void init(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_DB);
            statement.execute(INSERT);
        } catch (SQLException e) {
            System.out.println("Exception during ");
        }
    }
}
