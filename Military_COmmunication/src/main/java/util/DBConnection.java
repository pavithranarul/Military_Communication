package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            // Use the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Make sure the database URL, username, and password are correct
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/military_db", "root", "Pavi3@arul");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // Just for testing the connection
    public static void main(String[] args) {
        Connection testCon = DBConnection.getConnection();
        if (testCon != null) {
            System.out.println("Database connection successful");
        } else {
            System.out.println("Database connection failed");
        }
    }
}
