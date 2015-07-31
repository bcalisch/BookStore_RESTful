package com.claimacademy.claimazon.dao;

import com.claimacademy.claimazon.model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BookDAOImp implements BookDAO {


    @Override
    public ArrayList<Book> findAllBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        String sql = "Select * from Book B;";
        ResultSet rs = new SQLConnection().selectBuilder(sql);
        try {
            books = new Transformer().transformResultSetToBooks(rs);
            rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return books;
    }


    @Override
    public ArrayList<Book> findBookById(String id) {
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            String sqlID = "Select * from Book B WHERE B.ID = " + id;
            ResultSet rs = new SQLConnection().selectBuilder(sqlID);
            ;
            books = new Transformer().transformResultSetToBooks(rs);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public ArrayList<Book> findBooksByCategory(String name) {
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            String sql = "Select * from BookStore.Book b\n" +
                    "join BookStore.BookCategory bc on bc.Book_ID = b.ID\n" +
                    "where bc.name = \'" + name + "\'";
            ResultSet rs = new SQLConnection().selectBuilder(sql);
            books = new Transformer().transformResultSetToBooks(rs);
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateBook(String id) {

    }

    @Override
    public String deleteBook(String id) {
        String result="Everything Worked Fine";
        String sql1 = "Delete from BookStore.BookAuthors where BookStore.BookAuthors.Books_ID ="+id+"\n";
        String sql2= "Delete from BookStore.BookCategory where BookStore.Bookcategory.Book_ID = "+id+"\n";
        String sql3 = "Delete from BookStore.Book where BookStore.Book.ID =" + id ;
        try {
            new SQLConnection().getConnection().createStatement().execute(sql1);
            new SQLConnection().getConnection().createStatement().execute(sql2);
            new SQLConnection().getConnection().createStatement().execute(sql3);

        } catch (SQLException e) {
            e.printStackTrace();
            result = "Did not work, please try again";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String addBook(Book book) {
        String result = "Working";
        String sql = "Insert into BookStore.Book set(Title, Price, Year_Published, Publisher, Description)+" +
                "values(\'"+book.getTitle()+"\',"+book.getPrice()+",\'"+book.getYearPublished()+"\',\'"+book.getPublisher()+"\',\'"+book.getDescription()+"\'";
        try {
            Connection conn = new SQLConnection().getConnection();
            conn.createStatement().execute(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            result="That didn't work";
        }
        return result;
    }
}
