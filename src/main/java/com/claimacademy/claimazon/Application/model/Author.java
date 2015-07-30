package com.claimacademy.claimazon.Application.model;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;

/**
 * Created by benjamin on 7/29/15.
 */
@Controller
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBooksWrittenByAuthor(ArrayList<Book> booksWrittenByAuthor) {
        this.booksWrittenByAuthor = booksWrittenByAuthor;
    }
}
