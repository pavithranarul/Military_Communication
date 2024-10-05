package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import util.DBConnection;

public class UserDao {
    public User validateUser(String username, String password) {
        Connection con = DBConnection.getConnection();
        User user = null;

        // Check if the connection is null
        if (con == null) {
            System.out.println("Connection to the database failed.");
            return null;
        }

        try {
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                System.out.println("User validated: " + user.getUsername());
            } else {
                System.out.println("Invalid username or password for user: " + username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Make sure to close the connection and other resources (if needed)
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
