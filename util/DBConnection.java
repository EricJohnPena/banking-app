package util;

import java.sql.*;

public class DBConnection {
    

    // Get connection
    public static Connection getConnection() throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/bankdb";
        final String dbUser = "myuser";
        final String dbPassword = "mypassword";
        System.out.println("Connecting to the database...");
        return DriverManager.getConnection(url, dbUser, dbPassword);
    
    }
}