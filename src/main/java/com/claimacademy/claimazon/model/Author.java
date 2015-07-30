package com.claimacademy.claimazon.model;

import java.util.ArrayList;

/**
 * Created by benjamin on 7/29/15.
 */
public class Author {
    private String firstName;
    private String lastName;
    private ArrayList<Book> booksWrittenByAuthor;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooksWrittenByAuthor(ArrayList<Book> booksWrittenByAuthor) {
        this.booksWrittenByAuthor = booksWrittenByAuthor;
    }
}
