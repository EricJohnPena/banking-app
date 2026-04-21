package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDAO {
    private final String url = "jdbc:postgresql://localhost:5432/bankdb";
    private final String dbUser = "myuser";
    private final String dbPassword = "mypassword";

    // Get connection
    public Connection connect() throws SQLException {
        System.out.println("Connecting to the database...");
        return DriverManager.getConnection(url, dbUser, dbPassword);
    }

}
