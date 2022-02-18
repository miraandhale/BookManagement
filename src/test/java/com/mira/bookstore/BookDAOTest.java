package com.mira.bookstore;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;


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
        Assert.assertNotNull(String.valueOf(jdbcConnection), true);
    }

    @Test
    @DisplayName("Insert Book")
    public void insertBookTest() throws SQLException {
        BookDAO bookDAO = new BookDAO();
        BookDAO.connect();
        book.setId(38);
        book.setTitle("Python");
        book.setAuthor("Donals E. Kunth");
        book.setPrice(700);
        bookDAO.insertBook(book);
        Book book = bookDAO.getBook(38);
        assertEquals("Donals E. Kunth", book.getAuthor());
    }

    @Test
    void deleteBookTest() throws SQLException {
        Book book = bookDAO.getBook(38);
        bookDAO.deleteBook(book);
        assertNull(bookDAO.getBook(38));
    }

    @Test
    @DisplayName("Update Book")
    void updateBookTest() throws SQLException {
        BookDAO bookDAO = new BookDAO();
        Book book =bookDAO.getBook(5);
        book.setPrice(200);
        bookDAO.updateBook(book);
        Book book1 =bookDAO.getBook(5);
        assertEquals(200, book1.getPrice());
    }

    @Test
    @DisplayName("Retrieve book By ID")
    void getBook() throws SQLException {
        Book book = bookDAO.getBook(2);
        Assert.assertEquals("Advanced Java", book.getTitle());
        assertEquals("John", book.getAuthor());
        Assert.assertEquals(600, book.getPrice());
    }


    @Test
    @DisplayName("Retrive All books")
    public void ListBooksTest() throws SQLException {
        String sql = "Select * from book";
        Connection jdbcConnection = BookDAO.connect();
        Statement statement = jdbcConnection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            float price = rs.getFloat("price");
            System.out.println(id + " " + title + " " + author + " " + " " + price);
        }
        }
}