package com.claimacademy.claimazon.Application.service;

import com.claimacademy.claimazon.Application.dao.BookDAOImp;
import com.claimacademy.claimazon.Application.model.Book;
import com.claimacademy.claimazon.Application.model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
@Controller
@RestController
public class BookService {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
    @RequestMapping("/books")
    public ArrayList<Book> getAllBooks(){
        return new BookDAOImp().findAllBooks();
    }
    @RequestMapping("/books/{id}")
    public ArrayList<Book> getBooksByID(@PathVariable String id){
        return new BookDAOImp().findBooksById(id);
    }
}
