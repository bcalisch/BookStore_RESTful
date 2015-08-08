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
    public SQLConnection connection;
    public Transformer(){
        if (connection == null){
            connection  = new SQLConnection();
        }
    }
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
                book.setImageName(rs.getString("ImageName"));
                book.setAuthors(getAuthors(book.getId()));
                book.setCategories(getCategories(book.getId()));
                books.add(book);

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private ArrayList<Category> getCategories(int id) {
        ArrayList<Category> categories = new ArrayList<Category>();

        String sqlCategory="Select * from BookCategory C\n" +
                "\n" +
                "join Book B on B.ID = C.Book_ID\n" +
                "\n" +
                "where B.ID ="+id;
        try {

            ResultSet rsCategory = connection.selectBuilder(sqlCategory);;
            categories = transformRsToCategory(rsCategory);
            rsCategory.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public ArrayList<Category> transformRsToCategory(ResultSet rsCategory) {
        ArrayList<Category> categories = new ArrayList<Category>() ;
        try {
            while(rsCategory.next()){
                Category category = new Category();
                category.setName(rsCategory.getString("Name"));

                categories.add(category);
            }
            rsCategory.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    private ArrayList<Author> getAuthors(int id) {
        ArrayList<Author> authors = new ArrayList<Author>();

        try {
            String sqlAuthor="Select * from BookStore.Author A \n" +
                    "join BookStore.BookAuthors BA on A.ID = BA.Authors_ID\n" +
                    "                   \n" +
                    "                    where BA.Books_ID = "+id;
            ResultSet rsAuthor = connection.selectBuilder(sqlAuthor);
            authors = transformRsToAuthor(rsAuthor);
            rsAuthor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }

    public ArrayList<Author> transformRsToAuthor(ResultSet rsAuthor) {
        ArrayList<Author> authors = new ArrayList<Author>();
        try {
            while(rsAuthor.next()){
                Author author = new Author();
//                author.setId(rsAuthor.getIIDs_ID"));
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

    public ArrayList<Category> transformResultSetToCategoryCount(ResultSet rs) {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            while(rs.next()){
                Category category = new Category();
                category.setCount(rs.getInt("Count"));
                category.setName(rs.getString("Name"));

                categories.add(category);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
