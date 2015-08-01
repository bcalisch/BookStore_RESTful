package com.claimacademy.claimazon.model;

/**
 * Created by benjamin on 7/29/15.
 */

public class Author {
    private int id;
    private String firstName;
    private String lastName;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
