package com.claimacademy.claimazon.dao;


import com.claimacademy.claimazon.model.Book;

import java.util.ArrayList;

public interface BookDAO {
    public ArrayList<Book> findAll();
    public ArrayList<Book> findByTitle(String id);
    public void updateBook(String id);
    public void deleteBook(String id);
}
