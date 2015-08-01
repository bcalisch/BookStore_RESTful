package com.claimacademy.claimazon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by benjamin on 7/31/15.
 */
public class SQLConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/BookStore";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public ResultSet selectBuilder(String sqlStatement) {
        ResultSet rs = null;
        try {
            rs = getConnection().createStatement().executeQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Connection getConnection()  {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}



