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

    private ConnectionFactory(){
        //private constructor
    }

    /**
     * Connection instance
     */
    private Connection connection = getConnection();

    private static ConnectionFactory instance = null;

    public static ConnectionFactory getInstance(){
        if (instance==null)
            instance = new ConnectionFactory();
        return instance;
    }

    /**
     * Getting connection from connection pool.
     *
     * @see ConnectionFactory
     * @throws SQLException
     */
    public Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String username = "cruvadoiktrlmm";
        String password = "37d74a543444c5244a3d99b138332f75d91a822850815ac67bc1dfb5b87af438";
        String dbUrl = "jdbc:postgresql://" + "ec2-54-217-195-234.eu-west-1.compute.amazonaws.com:5432"
                + "/d6h02davsb03gm?sslmode=require";
        try {
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return connection;
    }

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
