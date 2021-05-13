package com.mixajlenko.epam.finaltask.ispsystem.model.dao.factory;

import com.mixajlenko.epam.finaltask.ispsystem.model.dao.exception.DAOException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {
    private final Properties props = new Properties();
    private Connection connection;

    public DaoFactory(String filename) {
        try (InputStream is = new FileInputStream(filename)) {
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Properties file is missing", e);
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"),
                        props.getProperty("db.password"));
            } catch (SQLException e) {
                throw new DAOException("No connection", e);
            }
        }
        return connection;
    }
}
