package com.mira.bookstore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.sql.*;

public class TestBookDAO {

    BookDAO bookDAO;
    Book book;
    @BeforeEach
    void init() throws SQLException {
        bookDAO = new BookDAO();
        book = new Book();
        BookDAO.connect();
        Connection jdbcConnection = BookDAO.connect();
    }

    @Test
    @DisplayName("Connection Test")
    public void connectTest() throws SQLException{
        Connection jdbcConnection = BookDAO.connect();
        Assert.assertNotNull(String.valueOf(jdbcConnection), true);
    }

    @Test
    @DisplayName("Retrive All books")
    public void ListBooksTest() throws SQLException {
        String sql = "Select * from book";
        Connection jdbcConnection = BookDAO.connect();
        Statement statement = jdbcConnection.createStatement();
       ResultSet rs = statement.executeQuery(sql);
       while(rs.next()){
           int id = rs.getInt("id");
           String title = rs.getString("title");
           String author = rs.getString("author");
           float price = rs.getFloat("price");
           System.out.println(id+" "+title+" "+author+" "+" "+price);
       }

    }
    @Test
    public void testinsert() throws SQLException {
        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
        Connection jdbcConnection = BookDAO.connect();

        PreparedStatement p = jdbcConnection.prepareStatement(sql);

        p.setString(1, "Advanced Java Programming");
        p.setString(2, "James");
        p.setFloat(3, 400);
        boolean rowInserted = p.executeUpdate() > 0;
        System.out.println("row inserted");
    }

    @Test
    @DisplayName("Insert Book")
    public void insertBookTest() throws SQLException {
        BookDAO.connect();
        book = new Book();
        book.setId(5);
        book.setTitle("Java");
        book.setAuthor("Jems");
        book.setPrice((float) 250.60);
        bookDAO.insertBook(book);
        Book dbData = bookDAO.getBook(5);
        Assert.assertEquals("Java",dbData.getTitle());
    }

    @Test
    public void getBookTest() throws SQLException {
       Book book = bookDAO.getBook(1);
        Assert.assertEquals("Core Java", book.getTitle());
        Assert.assertEquals("James", book.getAuthor());
        Assert.assertEquals(679.54, book.getPrice());
    }



}
