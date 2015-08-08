package com.claimacademy.claimazon.dao;


import com.claimacademy.claimazon.model.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDAO {
    public ArrayList<Book> findAllBooks() throws SQLException;
    public ArrayList<Book> findBookById(String id);
    public ArrayList<Book> findBooksByCategory(String name);
    public void updateBook(String id);
    public String deleteBook(String id);
    public String addBook(Book book);
}
