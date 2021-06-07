package com.mixajlenko.finaltask.ispsystem.dao.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    /**
     *
     * Connection pool for connect with Heroku
     *
     */
    private ConnectionFactory() {
        //private constructor
    }

    private static ConnectionFactory instance = null;

    public static synchronized ConnectionFactory getInstance() {
        if (instance == null)
            instance = new ConnectionFactory();
        return instance;
    }

    public Connection getConnection() {
        try {
            return dataSource();
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection dataSource() throws URISyntaxException, SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String ConnectionString = "jdbc:postgresql://ec2-54-217-195-234.eu-west-1.compute.amazonaws.com:5432/d6h02davsb03gm?sslmode=require";
        String username = "cruvadoiktrlmm";
        String password = "37d74a543444c5244a3d99b138332f75d91a822850815ac67bc1dfb5b87af438";

        return DriverManager.getConnection(ConnectionString, username, password);

    }

/**
 *
 * Connection pool for connect locally
 *
 */
//    private static ConnectionFactory pool;
//    private final DataSource dataSource;
//
//    public ConnectionFactory() throws NamingException {
//        Context initialContext = new InitialContext();
//        dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/d6h02davsb03gm");
//    }
//
//    public static synchronized ConnectionFactory getInstance() throws NamingException {
//        if (pool == null) {
//            pool = new ConnectionFactory();
//        }
//        return pool;
//    }
//
//    public Connection getConnection() throws SQLException {
//        return dataSource.getConnection();
//    }
}
