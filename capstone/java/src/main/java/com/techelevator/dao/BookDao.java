package com.techelevator.dao;

import com.techelevator.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> getBooks(int familyId);

    Book getBookById(int id);

}