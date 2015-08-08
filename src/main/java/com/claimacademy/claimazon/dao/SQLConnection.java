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
    protected Connection conn;

    static final String USER = "root";
    static final String PASS = "";
    public SQLConnection(){
        try {

            if (conn == null){
                if (DriverManager.getConnection(DB_URL, USER, PASS).isClosed()){
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                }
                else{
                    DriverManager.getConnection(DB_URL, USER, PASS).close();
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                }
            }
             else{
                conn.close();
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public ResultSet selectBuilder(String sqlStatement) {
        ResultSet rs = null;
        try {
            rs = getConnection().createStatement().executeQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Connection getConnection() {
        try {
            if(conn == null) {
                if (conn.isClosed()) {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                } else {
                    conn.close();
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                }
            }
            else{
                conn.close();
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }


        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


}



