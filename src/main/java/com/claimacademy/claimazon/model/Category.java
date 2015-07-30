package com.claimacademy.claimazon.model;

import java.util.ArrayList;

/**
 * Created by benjamin on 7/29/15.
 */
public class Category {
    private String name;
    private ArrayList<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
