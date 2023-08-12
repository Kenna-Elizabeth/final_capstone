package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Book;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBookDao implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getBooks(int familyId, int userId) {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT books.book_id, books.family_id, books.isbn, books.title, books.author, books.cover_url, books.note, " +
                "COALESCE(ub.completed, false) AS completed, " +
                "COALESCE(ub.recommended, false) AS recommended, " +
                "EXISTS( SELECT 1 FROM sessions WHERE sessions.book_id = books.book_id AND sessions.user_id = 1 ) AS progress " +
                "FROM books " +
                "LEFT JOIN users_books AS ub " +
                "ON books.book_id = ub.book_id AND ub.user_id = ?" +
                "WHERE books.family_id = ? " +
                "ORDER BY completed ASC, progress DESC, recommended DESC, title ASC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, familyId);
            while (results.next()) {
                Book book = mapRowToBook(results);
                books.add(book);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return books;
    }

    @Override
    public Book getBookById(int id, int userId) {
        Book book = null;

        String sql = "SELECT books.book_id, books.family_id, books.isbn, books.title, books.author, books.cover_url, books.note, " +
                "COALESCE(ub.completed, false) AS completed, " +
                "COALESCE(ub.recommended, false) AS recommended, " +
                "EXISTS( SELECT 1 FROM sessions WHERE sessions.book_id = books.book_id AND sessions.user_id = 1 ) AS progress " +
                "FROM books " +
                "LEFT JOIN users_books AS ub " +
                "ON books.book_id = ub.book_id AND ub.user_id = ?" +
                "WHERE books.book_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, id);
            if (results.next()) {
                book = mapRowToBook(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return book;
    }

    @Override
    public Book addBook(Book book, int familyId, int userId) {
        Book newBook = null;

        String sql = "INSERT INTO books (family_id, isbn, title, author, cover_url, note) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING book_id;";
        try {
            int newBookId = jdbcTemplate.queryForObject(sql, int.class, familyId, book.getIsbn(), book.getTitle(),
                    book.getAuthor(), book.getCoverUrl(), book.getNote());
            newBook = getBookById(newBookId, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
        return newBook;
    }

    private Book mapRowToBook(SqlRowSet rs) {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setFamilyId(rs.getInt("family_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setCoverUrl(rs.getString("cover_url"));
        book.setNote(rs.getString("note"));
        book.setCompleted(rs.getBoolean("completed"));
        book.setRecommended(rs.getBoolean("recommended"));
        book.setProgress(rs.getBoolean("progress"));
        return book;
    }

}
