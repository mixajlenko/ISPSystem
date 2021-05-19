package com.mixajlenko.epam.finaltask.ispsystem.dao.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFactory {

//    private final Properties props = new Properties();
//    private Connection connection;
//
//    public static ConnectionFactory getInstance() {
//        return new ConnectionFactory();
//    }
//
//    public Connection getConnection() {
//        try (InputStream is = new FileInputStream("src/main/resources/app.properties")) {
//            props.load(is);
//            if (connection == null) {
//                connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"),
//                        props.getProperty("db.password"));
//            }
//        } catch (SQLException | IOException throwables) {
//            throwables.printStackTrace();
//        }
//        return connection;
//    }
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
        return dataSource.getConnection();
    }
}
