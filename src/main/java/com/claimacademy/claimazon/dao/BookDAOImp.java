package com.claimacademy.claimazon.dao;

import com.claimacademy.claimazon.model.Author;
import com.claimacademy.claimazon.model.Book;
import com.claimacademy.claimazon.model.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BookDAOImp implements BookDAO {


    @Override
    public ArrayList<Book> findAllBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        String sql = "Select * from Book B;";
        ResultSet rs = new SQLConnection().selectBuilder(sql);
        try {
            books = new Transformer().transformResultSetToBooks(rs);
            rs.close();
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
            ResultSet rs = new SQLConnection().selectBuilder(sqlID);
            ;
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
            ResultSet rs = new SQLConnection().selectBuilder(sql);
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
            new SQLConnection().getConnection().createStatement().execute(sql1);
            new SQLConnection().getConnection().createStatement().execute(sql2);
            new SQLConnection().getConnection().createStatement().execute(sql3);

        } catch (SQLException e) {
            e.printStackTrace();
            result = "Did not work, please try again";
        }
        return result;
    }

    @Override
    public String addBook(Book book) {
        String result = "Working";
        String sqlBookAuthor;
        String sqlNewAuthor, sqlNewCategory;
        String sqlCategory;
        int authorID;
        String categoryName;
        int authorIDint;
//        System.out.println("book.getYearPublished() = " + book.getYearPublished().substring(0,4));
        /*Insert into BookStore.Book (Title, Price, Year_Published, Publisher, Description)
                values('Java For Beginners',22.32,'1999','Johan Bach',"Cool book about Java, yall");*/
        String sql = "Insert into BookStore.Book (ID ,Title, Price, Year_Published, Publisher, Description)" +
                "values("+book.getId()+",\'" + book.getTitle() + "\',\n" + book.getPrice() + ",\n \'" + book.getYearPublished().substring(0, 4) + "\',\'" + book.getPublisher() + "\',\n\n\'" + book.getDescription() + "\')";
        try {
            Connection conn = new SQLConnection().getConnection();
            conn.createStatement().execute(sql);
            for (Author author : book.getAuthors()) {
                authorID = getAuthorID(author.getFirstName(), author.getLastName());

                sqlBookAuthor = "Insert into BookStore.BookAuthors(Books_ID, Authors_ID) " +
                        "values(" + book.getId() + "," + authorID + ")";
                    conn.createStatement().execute(sqlBookAuthor);
            }
            for (Category category : book.getCategories()) {
                categoryName = getCategoryName(category.getName());
                if(categoryName==null){
                    sqlNewCategory = "Insert into BookStore.Category (Name)Values(\""+category.getName()+"\")";
                    conn.createStatement().execute(sqlNewCategory);
                }
                else{
                    category.setName(categoryName);
                }
                sqlCategory = "Insert into BookStore.BookCategory(Book_ID, Name) " +
                        "values(" + book.getId() + ",\'" + category.getName() + "\')";
                conn.createStatement().execute(sqlCategory);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "That didn't work";
        }
        return result;
    }

    protected String getCategoryName(String name) {
        ArrayList<Category> categories;
        String categoryName = null;
        String sql = "Select * from BookStore.Category C where (C.Name = \"" + name + "\")";
        ResultSet rs = new SQLConnection().selectBuilder(sql);
        categories = new Transformer().transformRsToCategory(rs);
        if (!categories.isEmpty()) {
            categoryName = categories.get(0).getName();
        }
        return categoryName;
    }

    protected int getAuthorID(String firstName, String lastName) {
        ArrayList<Author> authors;
        int authorIDint=0;
        String authorID=null;
        Connection conn =  new SQLConnection().getConnection();
        String sql = "Select * from BookStore.Author A where (A.First_Name = \"" + firstName + "\") AND " +
                "(A.Last_Name = \"" + lastName + "\")";
        ResultSet rs = new SQLConnection().selectBuilder(sql);
        authors = new Transformer().transformRsToAuthor(rs);
        if (!authors.isEmpty()) {
            authorIDint = (authors.get(0).getId());
        }
        if (authorIDint == 0) {
            sql = "Insert into BookStore.Author(First_Name, Last_Name) values(\""
                    + firstName + "\",\"" + lastName + "\")";
            try {
                conn.createStatement().execute(sql);
                sql = "Select * from BookStore.Author A where (A.First_Name = \"" + firstName + "\") AND " +
                        "(A.Last_Name = \"" + lastName + "\")";
                rs = new SQLConnection().selectBuilder(sql);
                authors =new Transformer().transformRsToAuthor(rs);
                authorIDint = authors.get(0).getId();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return authorIDint;
    }
}
