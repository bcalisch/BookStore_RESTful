package com.claimacademy.claimazon.dao;

import com.claimacademy.claimazon.model.Book;
import com.claimacademy.claimazon.model.Category;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by benjamin on 7/30/15.
 */
public class CategoryDAOImp implements CategoryDAO {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/BookStore";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    @Override
    public ArrayList<Category> findAllCategories(String name) {
        ArrayList<Category> categories = new ArrayList<Category>();

        try {
            Class.forName(JDBC_DRIVER);
            Connection connCategory = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmtCategory = connCategory.createStatement();
            String sqlCategory="Select * from BookStore.Category";
            ResultSet rsCategory = stmtCategory.executeQuery(sqlCategory);

            while(rsCategory.next()){
                Category category = new Category();
                categories.add(category);

            }

            rsCategory.close();
            stmtCategory.close();
            connCategory.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    private ArrayList<Book> getBooks(String name) {
        ArrayList<Book> books = new ArrayList<Book>();
        return books;
    }
}
