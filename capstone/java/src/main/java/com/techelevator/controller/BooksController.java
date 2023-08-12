package com.techelevator.controller;

import com.techelevator.dao.BookDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Book;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/books")
public class BooksController {

    private final BookDao bookDao;
    private final UserDao userDao;

    public BooksController(BookDao bookDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Book> getBooks(Principal userPrincipal) {
        User user = getUserFromPrincipal(userPrincipal);

        try {
            return bookDao.getBooks(user.getFamilyId(), user.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to Find Family Users");
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable int id, Principal userPrincipal) {
        User user = getUserFromPrincipal(userPrincipal);

        try {
            Book book = bookDao.getBookById(id, user.getId());
            if (book.getFamilyId() != user.getFamilyId()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Book Not In Collection");
            }
            return book;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to Find Book");
        }
    }

    @RequestMapping(path = "/recommended", method = RequestMethod.GET)
    public Book getRecommendedBook(Principal userPrincipal) {
        User user = getUserFromPrincipal(userPrincipal);

        try {
            return bookDao.getRecommendedBook(user.getFamilyId(), user.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to Find Book");
        }

    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book, Principal userPrincipal) {
        User user = getUserFromPrincipal(userPrincipal);

        Book newBook;
        try {
            newBook = bookDao.addBook(book, user.getFamilyId(), user.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error adding book");
        }
        return newBook;
    }

    //Private Methods

    //TODO instead of making a Dao call for user details, add info to Principal by extending User
    //https://stackoverflow.com/questions/20349594/adding-additional-details-to-principal-object-stored-in-spring-security-context
    private User getUserFromPrincipal(Principal userPrincipal) {
        try {
            return userDao.getUserByUsername(userPrincipal.getName());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Not Found");
        }
    }

}
