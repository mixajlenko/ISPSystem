package com.mixajlenko.finaltask.ispsystem.dao.connection;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final Logger logger = Logger.getLogger(ConnectionFactory.class);

    private static ConnectionFactory pool;
    private final DataSource dataSource;


    public ConnectionFactory() throws NamingException {
        Context initialContext = new InitialContext();
        dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/ISPManager");
    }

    public static synchronized ConnectionFactory getInstance() throws NamingException {
        if (pool == null) {
            pool = new ConnectionFactory();
        }
        return pool;
    }

    public Connection getConnection() throws SQLException {
        logger.info("Create connection");
        return dataSource.getConnection();
    }
}
