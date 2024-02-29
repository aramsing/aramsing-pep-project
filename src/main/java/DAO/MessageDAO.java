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
     * TODO: Retrieve all messages from the message table
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
     * TODO: Retrieve an individual message by its id
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
     * TODO: Create a new message here
     */
    public Message insertMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            // SQL logic
            String sql = "INSERT INTO message (message_id, posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // prepared statement logic
            preparedStatement.setInt(1, message.getMessage_id());
            preparedStatement.setInt(2, message.getPosted_by());
            preparedStatement.setString(3, message.getMessage_text());
            preparedStatement.setLong(4, message.getTime_posted_epoch());

            preparedStatement.executeQuery();
            return message;
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /*
     * TODO: Update a message here
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
            preparedStatement.setInt(3, message.getMessage_id());

            preparedStatement.executeQuery();
        }
        
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
