package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.User;

import java.util.List;

public interface BookDao {

    List<Book> getBooks(int familyId, int userId);

    Book getBookById(int id, int userId);

    Book getRecommendedBook(int familyId, int userId);

    Book addBook(Book book, int familyId, int userId);

    void setBookCompleted(int bookId, boolean completed, int userId);
}
