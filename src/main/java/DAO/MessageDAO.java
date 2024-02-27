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
        return null;
    }

    /*
     * TODO: Retrieve an individual message by its id
     */
    public Message getMessageByID(int id) {
        Connection connection = ConnectionUtil.getConnection();

        try{
            // SQL logic
            String sql = ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // prepared statement set logic if any

            // result set logic
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                // additional logic
            }
        }

        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

    public Message insertMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        return null;
    }
}
