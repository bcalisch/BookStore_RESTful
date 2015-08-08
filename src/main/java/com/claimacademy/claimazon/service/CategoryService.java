package com.claimacademy.claimazon.service;

import com.claimacademy.claimazon.dao.CategoryDAO;
import com.claimacademy.claimazon.dao.CategoryDAOImp;
import com.claimacademy.claimazon.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
@RestController
public class CategoryService {
    /*   private BookDAO bookDAO = new BookDAOImp();

    @RequestMapping(value="books",method=RequestMethod.GET)
    public ArrayList<Book> getAllBooks(){
        return bookDAO.findAllBooks();
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
    }*/
    private CategoryDAO categoryDAO = new CategoryDAOImp();
    @RequestMapping(value="categories",method= RequestMethod.GET)
    public ArrayList<Category> cate(){
        return categoryDAO.categoryCount();
    }
}
