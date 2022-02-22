package com.mira.bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//added new comment
class BookDAOTest {
    BookDAO bookDAO;
    Book book;

    @BeforeEach
    public void init(){
        bookDAO = new BookDAO();
        book = new Book();
    }

    @Test
    @DisplayName("Connection Test")
    public void connectTest() throws SQLException{
        Connection jdbcConnection = BookDAO.connect();
        assertNotNull(jdbcConnection);
    }

    @Test
    @DisplayName("Insert Book")
    public void insertBookTest() throws SQLException {
        book.setId(4);
        book.setTitle("Advanced Java");
        book.setAuthor("Donald");
        book.setPrice(430);
        bookDAO.insertBook(book);
        Book book = bookDAO.getBook(4);
        assertEquals("Donald", book.getAuthor());
    }

    @Test
    void deleteBookTest() throws SQLException {
        Book book = bookDAO.getBook(4);
        bookDAO.deleteBook(book);
        assertNull(bookDAO.getBook(4));
    }

    @Test
    @DisplayName("Update Book")
    void updateBookTest() throws SQLException {
        Book book =bookDAO.getBook(1);
        book.setPrice(300);
        bookDAO.updateBook(book);
        Book book1 =bookDAO.getBook(1);
        assertEquals(300, book1.getPrice());
    }

    @Test
    @DisplayName("Retrieve book By ID")
    void getBook() throws SQLException {
        Book book = bookDAO.getBook(2);
        assertAll(
                () -> assertEquals("Computer Programming", book.getTitle()),
                () -> assertEquals("john", book.getAuthor()),
                () -> assertEquals(600, book.getPrice())
        );
    }

    @Test
    @DisplayName("Retrieve table")
    void getBooks() throws SQLException {
        List<Book> book = bookDAO.listAllBooks();
        assertTrue(book.size()>0);
    }
}