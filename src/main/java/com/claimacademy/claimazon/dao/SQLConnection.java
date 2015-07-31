package com.claimacademy.claimazon.dao;

import com.claimacademy.claimazon.model.Author;
import com.claimacademy.claimazon.model.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private ArrayList<Category> getCategories(int id) {
        ArrayList<Category> categories = new ArrayList<Category>();

        try {
            String sqlCategory="Select C.Name from BookCategory C\n" +
                    "\n" +
                    "join Book B on B.ID = C.Book_ID\n" +
                    "\n" +
                    "where B.ID ="+id;
            ResultSet rsCategory = new SQLConnection().selectBuilder(sqlCategory);;
            while(rsCategory.next()){
                Category thisCategory = new Category();
                thisCategory.setName(rsCategory.getString("Name"));

                categories.add(thisCategory);
            }
            rsCategory.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    private ArrayList<Author> getAuthors(int id) {
        ArrayList<Author> authors = new ArrayList<Author>();

        try {

            String sqlAuthor="Select A.First_Name, A.Last_Name from BookStore.Author A \n" +
                    "join BookStore.BookAuthors BA on A.ID = BA.Authors_ID\n" +
                    "                   \n" +
                    "                    where BA.Books_ID = "+id;
            ResultSet rsAuthor = new SQLConnection().selectBuilder(sqlAuthor);;

            while(rsAuthor.next()){
                Author thisAuthor = new Author();
                thisAuthor.setFirstName(rsAuthor.getString("First_Name"));
                thisAuthor.setLastName(rsAuthor.getString("Last_Name"));

                authors.add(thisAuthor);
            }
            rsAuthor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }
}
