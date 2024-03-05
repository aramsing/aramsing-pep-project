package DAO;

import java.sql.*;
import java.util.*;
import Model.Message;
import Util.ConnectionUtil;

/**
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a
 * database. The methods here are mostly filled out, you will just need to add a SQL statement.
 * 
 * A database named message exists and contains the following values:
 * create table message (
 *   message_id int primary key auto_increment,
 *   posted_by int,
 *   message_text varchar(255),
 *   time_posted_epoch bigint,
 *   foreign key (posted_by) references  account(account_id)
 * );
 */
public class MessageDAO {
    /*
     * Retrieve all messages from the message table
     */
    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
            // SQL logic
            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Message message = new Message(
                    resultSet.getInt("message_id"),
                    resultSet.getInt("posted_by"),
                    resultSet.getString("message_text"),
                    resultSet.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

    /*
     * Retrieve an individual message by its id
     */
    public Message getMessageByID(int message_id) {
        Connection connection = ConnectionUtil.getConnection();

        try{
            // SQL logic
            String sql = "SELECT * FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // prepared statement set logic if any
            preparedStatement.setInt(1, message_id);

            // result set logic
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Message message = new Message(
                    resultSet.getInt("message_id"),
                    resultSet.getInt("posted_by"),
                    resultSet.getString("message_text"),
                    resultSet.getLong("time_posted_epoch")
                );
                return message;
            }
        }

        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

    /*
     * Get all messages by account id
     */
    public List<Message> getAllMessageByAccountID(int posted_by) {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
            // SQL logic here
            String sql = "SELECT * FROM message WHERE posted_by = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // prepared statement logic here
            preparedStatement.setInt(1, posted_by);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Message message = new Message(
                    resultSet.getInt("message_id"),
                    resultSet.getInt("posted_by"),
                    resultSet.getString("message_text"),
                    resultSet.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
        }
        
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

    /*
     * Create a new message here
     */
    public Message insertMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            // SQL logic
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // prepared statement logic
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedMessageID = (int) resultSet.getLong(1);
                return new Message(generatedMessageID, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /*
     * Update a message here
     */
    public void updateMessage(int message_id, Message message) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            // SQL logic
            String sql = "UPDATE message SET message_text = ?, time_posted_epoch = ? WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // prepared statement logic
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setLong(2, message.getTime_posted_epoch());
            preparedStatement.setInt(3, message_id);

            preparedStatement.executeUpdate();
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Delete a message here
     */
    public void deleteMessage(int message_id) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        try {
            // SQL logic goes here
            String sql = "DELETE FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // prepared statement logic
            preparedStatement.setInt(1, message_id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("Message does not exist in database");
            }
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
