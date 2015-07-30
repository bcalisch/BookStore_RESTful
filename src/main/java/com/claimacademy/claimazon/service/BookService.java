package com.claimacademy.claimazon.service;

import com.claimacademy.claimazon.dao.BookDAOImp;
import com.claimacademy.claimazon.model.Book;
import com.claimacademy.claimazon.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

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
        System.out.println("Test");
        ArrayList<Book> books = new BookDAOImp().findAll();
        return books;
    }

}
