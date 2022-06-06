package org.aleks4ay.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ConnectH2 {
    private static final Logger log = LoggerFactory.getLogger(ConnectH2.class);
    private Connection connection;

    private static final String DRIVER_NAME = "jdbc:h2:mem:test";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "sa";

    ConnectH2() {
        try {
            this.connection = DriverManager.getConnection(DRIVER_NAME, USER_NAME, PASSWORD);
            new DataBaseInit().init(connection);
        } catch (SQLException e) {
            log.error("SQLException during get Connection from resource {}. {}", DRIVER_NAME, e);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }


    public static void main(String[] args) {
        Connection connection = new ConnectH2().getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM event;");
            if (resultSet.next()) {
                System.out.println("id:" + resultSet.getLong("id"));
                System.out.println("title:" + resultSet.getString("title"));
                System.out.println("date_time:" + resultSet.getTimestamp("date_time"));
            }
        } catch (SQLException e) {
            System.out.println("Exception during ");
        }

    }

    @Override
    protected void finalize() throws Throwable {
        closeConnection(connection);
        super.finalize();
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.debug("SQLException during close connection from {}. {}", ConnectH2.class, e);
            }
        }
    }
}
