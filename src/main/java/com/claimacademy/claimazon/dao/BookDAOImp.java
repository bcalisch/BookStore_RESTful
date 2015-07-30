package com.claimacademy.claimazon.dao;

import com.claimacademy.claimazon.model.Author;
import com.claimacademy.claimazon.model.Book;
import com.claimacademy.claimazon.model.Category;

import java.sql.*;
import java.util.ArrayList;


public class BookDAOImp implements BookDAO {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/BookStore";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    @Override
    public ArrayList<Book> findAll() {
        ArrayList<Book> books = new ArrayList<Book>();
        String title;
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql="Select * from Book B;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Book thisBook = new Book();

                thisBook.setTitle(rs.getString("Title"));
                thisBook.setDescription(rs.getString("Description"));
                thisBook.setId(rs.getInt("ID"));
                thisBook.setPrice(rs.getDouble("Price"));
                thisBook.setPublisher(rs.getString("Publisher"));
                thisBook.setYearPublished(rs.getNString("Year_Published"));
                thisBook.setAuthors(getAuthors(thisBook.getId()));
                thisBook.setCategories(getCategories(thisBook.getId()));


                books.add(new Book());

//                int age = rs.getInt("age");

                //Display values
            }
            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;

    }

    private ArrayList<Category> getCategories(int id) {
        ArrayList<Category> categories = new ArrayList<Category>();

        try {
            Class.forName(JDBC_DRIVER);
            Connection connCategory = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmtCategory = connCategory.createStatement();
            String sqlCategory="Select C.Name from BookCategory C\n" +
                    "\n" +
                    "join Book B on B.ID = C.Book_ID\n" +
                    "\n" +
                    "where B.ID ="+id;
            ResultSet rsCategory = stmtCategory.executeQuery(sqlCategory);

            while(rsCategory.next()){
                Category thisCategory = new Category();
                thisCategory.setName(rsCategory.getString("Name"));

                categories.add(thisCategory);

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

    private ArrayList<Author> getAuthors(int id) {
        ArrayList<Author> authors = new ArrayList<Author>();

        try {
            Class.forName(JDBC_DRIVER);
            Connection connAuthor = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmtAuthor = connAuthor.createStatement();
            String sqlAuthor="Select A.First_Name, A.LastName from Author A \n" +
                    "join BookAuthors BA on A.ID = BA.Authors_ID\n" +
                    "\n" +
                    "where BA.Books_ID ="+id;
            ResultSet rsAuthor = stmtAuthor.executeQuery(sqlAuthor);

            while(rsAuthor.next()){
                Author thisAuthor = new Author();
                thisAuthor.setFirstName(rsAuthor.getString("First_Name"));
                thisAuthor.setLastName(rsAuthor.getString("Last_Name"));

                authors.add(thisAuthor);

                rsAuthor.close();
                stmtAuthor.close();
                connAuthor.close();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }

    @Override
    public ArrayList<Book> findByTitle(String id) {
        return null;
    }

    @Override
    public void updateBook(String id) {

    }

    @Override
    public void deleteBook(String id) {

    }
}
