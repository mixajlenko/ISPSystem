package com.mixajlenko.finaltask.ispsystem.dao.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory pool;
    private final DataSource dataSource;

    public ConnectionFactory() throws NamingException {
        Context initialContext = new InitialContext();
        dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/d6h02davsb03gm");
    }

    public static synchronized ConnectionFactory getInstance() throws NamingException {
        if (pool == null) {
            pool = new ConnectionFactory();
        }
        return pool;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
