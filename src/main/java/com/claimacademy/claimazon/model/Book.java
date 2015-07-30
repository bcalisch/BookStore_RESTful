package com.claimacademy.claimazon.model;

import java.util.ArrayList;

/**
 * Created by benjamin on 7/29/15.
 */
public class Book {
    private int id;
    private String title;
    private double price;
    private String description;
    private String publisher;
    private String yearPublished;
    private ArrayList<Author> authors= new ArrayList<Author>();
    private ArrayList<Category> categories = new ArrayList<Category>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }


    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
