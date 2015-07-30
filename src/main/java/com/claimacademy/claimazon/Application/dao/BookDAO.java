package com.claimacademy.claimazon.Application.dao;


import com.claimacademy.claimazon.Application.model.Book;

import java.util.ArrayList;

public interface BookDAO {
    public ArrayList<Book> findAllBooks();
    public ArrayList<Book> findBooksById(String id);
    public void updateBook(String id);
    public void deleteBook(String id);
}
