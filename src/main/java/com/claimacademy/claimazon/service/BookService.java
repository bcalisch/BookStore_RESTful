package com.claimacademy.claimazon.service;

import com.claimacademy.claimazon.dao.BookDAOImp;
import com.claimacademy.claimazon.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
@Controller
@RestController
public class BookService {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="books",method=RequestMethod.GET)
    public ArrayList<Book> getAllBooks(){
        return new BookDAOImp().findAllBooks();
    }
    @RequestMapping(value= "books/id/{id}",method=RequestMethod.GET)
    public ArrayList<Book> getBooksByID(@PathVariable String id){
        return new BookDAOImp().findBookById(id);
    }
    @RequestMapping(value = "books/category/{name}",method=RequestMethod.GET)
    public ArrayList<Book> getBooksByCategory(@PathVariable String name){
        return new BookDAOImp().findBooksByCategory(name);
    }
    @RequestMapping(value= "books/{id}", method=RequestMethod.DELETE)

    public String deleteBook(@PathVariable String id) {
        return new BookDAOImp().deleteBook(id);
    }
    @RequestMapping(value = "book", method=RequestMethod.POST)
    public Book create(@RequestBody Book book) {
        BookDAOImp bookDAOImp = new BookDAOImp();
        bookDAOImp.addBook(book);
        return book;
    }

}
