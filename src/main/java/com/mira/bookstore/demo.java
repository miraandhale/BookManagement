package com.mira.bookstore;

import java.sql.*;

public class demo {
    Connection jdbcConnection;
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "Mira@123");
        }

        Statement st = jdbcConnection.createStatement();
        ResultSet rs = st.executeQuery("select * from book");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
        }
    }
    public static void main(String[] args) throws SQLException {
        demo obj = new demo();
        obj.connect();
    }

}
