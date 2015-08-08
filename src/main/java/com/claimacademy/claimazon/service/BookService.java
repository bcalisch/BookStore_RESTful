package com.claimacademy.claimazon.service;

import com.claimacademy.claimazon.dao.BookDAO;
import com.claimacademy.claimazon.dao.BookDAOImp;
import com.claimacademy.claimazon.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
@Controller
@RestController
public class BookService {

    private BookDAO bookDAO = new BookDAOImp();

    @RequestMapping(value="books",method=RequestMethod.GET)
    public ArrayList<Book> getAllBooks(){

        try {
            return bookDAO.findAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value= "books/id/{id}",method=RequestMethod.GET)
    public ArrayList<Book> getBooksByID(@PathVariable String id){
        return bookDAO.findBookById(id);
    }

    @RequestMapping(value = "books/category/{name}",method=RequestMethod.GET)
    public ArrayList<Book> getBooksByCategory(@PathVariable String name){
        return  bookDAO.findBooksByCategory(name);
    }

    @RequestMapping(value= "books/{id}", method=RequestMethod.DELETE)
    public String deleteBook(@PathVariable String id) {
        return bookDAO.deleteBook(id);
    }

    @RequestMapping(value = "book", method=RequestMethod.POST)
    public Book create(@RequestBody Book book) {

        bookDAO.addBook(book);
        return book;
    }

}
