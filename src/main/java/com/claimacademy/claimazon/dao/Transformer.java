package com.claimacademy.claimazon.dao;

import com.claimacademy.claimazon.model.Author;
import com.claimacademy.claimazon.model.Book;
import com.claimacademy.claimazon.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by benjamin on 7/31/15.
 */
public class Transformer {
    public ArrayList<Book> transformResultSetToBooks(ResultSet rs){
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            while (rs.next()) {
                Book book = new Book();

                book.setTitle(rs.getString("Title"));
                book.setDescription(rs.getString("Description"));
                book.setId(rs.getInt("ID"));
                book.setPrice(rs.getDouble("Price"));
                book.setPublisher(rs.getString("Publisher"));
                book.setYearPublished(rs.getString("Year_Published"));
                book.setAuthors(getAuthors(book.getId()));
                book.setCategories(getCategories(book.getId()));


                books.add(book);

    //                int age = rs.getInt("age");

                //Display values
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private ArrayList<Category> getCategories(int id) {
        ArrayList<Category> categories = new ArrayList<Category>();

        String sqlCategory="Select C.Name from BookCategory C\n" +
                "\n" +
                "join Book B on B.ID = C.Book_ID\n" +
                "\n" +
                "where B.ID ="+id;
        try {

            ResultSet rsCategory = new SQLConnection().selectBuilder(sqlCategory);;
            while(rsCategory.next()){
                Category category = new Category();
                category.setName(rsCategory.getString("Name"));

                categories.add(category);
            }
            rsCategory.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    private ArrayList<Author> getAuthors(int id) {
        ArrayList<Author> authors = new ArrayList<Author>();

        try {
            String sqlAuthor="Select A.First_Name, A.Last_Name from BookStore.Author A \n" +
                    "join BookStore.BookAuthors BA on A.ID = BA.Authors_ID\n" +
                    "                   \n" +
                    "                    where BA.Books_ID = "+id;
            ResultSet rsAuthor = new SQLConnection().selectBuilder(sqlAuthor);;

            while(rsAuthor.next()){
                Author author = new Author();
                author.setFirstName(rsAuthor.getString("First_Name"));
                author.setLastName(rsAuthor.getString("Last_Name"));

                authors.add(author);
            }
            rsAuthor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }
}
