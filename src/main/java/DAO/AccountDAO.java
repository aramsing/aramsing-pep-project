package DAO;

import Model.Account;
import java.sql.*;
import Util.ConnectionUtil;

/**
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a
 * database. The methods here are mostly filled out, you will just need to add a SQL statement.
 * 
 * A database named message exists and contains the following values:
 * create table account (
 *  account_id int primary key auto_increment,
 *  username varchar(255) unique,
 *  password varchar(255)
 * );
 */
public class AccountDAO {
    /*
     * TODO: Insert an account into the account table
     * Create an account in our demo social media
     */
    public Account insertAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection(); // creates a connection to the database

        try {
            // SQL logic
            String sql = "INSERT INTO (username, password) VALUES (?, ?);"; // since the account_id is auto incrementing, we only need to create a user name and password
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // set string method, the user input starts at index 1 instead of 0
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            // SQL execution
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int generatedAccountId = (int) resultSet.getLong(1);
                return new Account(generatedAccountId, account.getUsername(), account.getPassword());
            }
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}