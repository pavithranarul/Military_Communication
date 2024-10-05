package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Message;
import util.DBConnection;

public class MessageDao {
    public void saveMessage(Message message) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBConnection.getConnection();
            String query = "INSERT INTO messages (content, sender_id, receiver_id) VALUES (?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, message.getContent());
            pst.setInt(2, message.getSenderId());
            pst.setInt(3, message.getReceiverId());

            // Debugging: Print the SQL statement
            System.out.println("Executing SQL: " + pst.toString());

            pst.executeUpdate();
            System.out.println("Message saved successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while saving message: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
