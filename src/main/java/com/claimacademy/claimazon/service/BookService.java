package com.claimacademy.claimazon.service;

import com.claimacademy.claimazon.dao.BookDAOImp;
import com.claimacademy.claimazon.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
@Controller
@RestController
public class BookService {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("Get/books")
    public ArrayList<Book> getAllBooks(){
        return new BookDAOImp().findAllBooks();
    }
    @RequestMapping("Get/books/id/{id}")
    public ArrayList<Book> getBooksByID(@PathVariable String id){
        return new BookDAOImp().findBookById(id);
    }
    @RequestMapping("Get/books/category/{name}")
    public ArrayList<Book> getBooksByCategory(@PathVariable String name){
        return new BookDAOImp().findBooksByCategory(name);
    }
    @RequestMapping("Delete/books/id/{id}")

    public String deleteBook(@PathVariable String id) {
        return new BookDAOImp().deleteBook(id);

    }
    @RequestMapping(value = "Post/book/{book}", method = RequestMethod.POST)

    public String addBook(@PathVariable Book book) {
        return new BookDAOImp().addBook(book);

    }
/*@RequestMapping(value = "/submitBook", method = RequestMethod.POST)
	public void setNewBook(@RequestBody Book book) {

		bookListDao.submitBook(book);

	}*/
/**/
}
