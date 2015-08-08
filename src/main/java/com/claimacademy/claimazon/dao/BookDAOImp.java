package com.claimacademy.claimazon.dao;

import com.claimacademy.claimazon.model.Author;
import com.claimacademy.claimazon.model.Book;
import com.claimacademy.claimazon.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BookDAOImp implements BookDAO {

    private SQLConnection connection = new SQLConnection();


    @Override
    public ArrayList<Book> findAllBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        String sql = "Select * from Book B;";
        ResultSet rs = connection.selectBuilder(sql);
        try {
            books = new Transformer().transformResultSetToBooks(rs);
            rs.close();
            connection.conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return books;
    }


    @Override
    public ArrayList<Book> findBookById(String id) {
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            String sqlID = "Select * from Book B WHERE B.ID = " + id;
            ResultSet rs = connection.selectBuilder(sqlID);
            books = new Transformer().transformResultSetToBooks(rs);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public ArrayList<Book> findBooksByCategory(String name) {
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            String sql = "Select * from BookStore.Book b\n" +
                    "join BookStore.BookCategory bc on bc.Book_ID = b.ID\n" +
                    "where bc.name = \'" + name + "\'";
            ResultSet rs = connection.selectBuilder(sql);
            books = new Transformer().transformResultSetToBooks(rs);
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateBook(String id) {

    }

    @Override
    public String deleteBook(String id) {
        String result = "Everything Worked Fine";
        String sql1 = "Delete from BookStore.BookAuthors where BookStore.BookAuthors.Books_ID =" + id + "\n";
        String sql2 = "Delete from BookStore.BookCategory where BookStore.Bookcategory.Book_ID = " + id + "\n";
        String sql3 = "Delete from BookStore.Book where BookStore.Book.ID =" + id;
        try {
            connection.getConnection();
            connection.conn.createStatement().execute(sql1);
            connection.conn.createStatement().execute(sql2);
            connection.conn.createStatement().execute(sql3);

        } catch (SQLException e) {
            e.printStackTrace();
            result = "Did not work, please try again";
        }
        return result;
    }

    @Override
    public String addBook(Book book) {
        cleanBook(book);
        String result = "Working";
        String sqlBookAuthor;
        String sqlNewCategory;
        String sqlCategory;
        int authorID;
        String categoryName;

        String sql = "Insert into BookStore.Book (ID ,Title, Price, Year_Published, Publisher, Description, ImageName)" +
                "values("+book.getId()+",\'" + book.getTitle() + "\',\n" + book.getPrice() + ",\n \'" + book.getYearPublished().substring(0, 4) + "\',\'" + book.getPublisher() + "\',\n\n\'" + book.getDescription() + "\',\'"+book.getImageName()+"\')";
        try {
            connection.getConnection().createStatement().execute(sql);
            for (Author author : book.getAuthors()) {
                authorID = getAuthorID(author.getFirstName(), author.getLastName());
                sqlBookAuthor = "Insert into BookStore.BookAuthors(Books_ID, Authors_ID) " +
                        "values(" + book.getId() + "," + authorID + ")";
                connection.getConnection().createStatement().execute(sqlBookAuthor);
            }
            for (Category category : book.getCategories()) {
                categoryName = getCategoryName(category.getName());
                if(categoryName==null){
                    sqlNewCategory = "Insert into BookStore.Category (Name)Values(\""+category.getName()+"\")";
                    connection.getConnection().createStatement().execute(sqlNewCategory);
                }
                else{
                    category.setName(categoryName);
                }
                sqlCategory = "Insert into BookStore.BookCategory(Book_ID, Name) " +
                        "values(" + book.getId() + ",\'" + category.getName() + "\')";
                connection.getConnection().createStatement().execute(sqlCategory);
            }
            connection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "That didn't work";
        }
        return result;
    }

    private void cleanBook(Book book) {
        book.setDescription(book.getDescription().replaceAll("\'", " \\'"));
    }

    protected String getCategoryName(String name) {
        ArrayList<Category> categories;
        String categoryName = null;
        String sql = "Select * from BookStore.Category C where (C.Name = \"" + name + "\")";
        ResultSet rs = connection.selectBuilder(sql);
        categories = new Transformer().transformRsToCategory(rs);
        if (!categories.isEmpty()) {
            categoryName = categories.get(0).getName();
        }
        return categoryName;
    }

    protected int getAuthorID(String firstName, String lastName) {
        ArrayList<Author> authors;
        int authorIDint=0;
        String sql = "Select * from BookStore.Author A where (A.First_Name = \"" + firstName + "\") AND " +
                "(A.Last_Name = \"" + lastName + "\")";
        ResultSet rs = connection.selectBuilder(sql);
        authors = new Transformer().transformRsToAuthor(rs);
        if (!authors.isEmpty()) {
            authorIDint = (authors.get(0).getId());
        }
        else if (authorIDint == 0) {
            sql = "Insert into BookStore.Author(First_Name, Last_Name) values(\""
                    + firstName + "\",\"" + lastName + "\")";
            try {
                connection.getConnection().createStatement().execute(sql);
                sql = "Select * from BookStore.Author A where (A.First_Name = \"" + firstName + "\") AND " +
                        "(A.Last_Name = \"" + lastName + "\")";
                rs = connection.selectBuilder(sql);
                authors =new Transformer().transformRsToAuthor(rs);
                authorIDint = authors.get(0).getId();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return authorIDint;
    }
}
