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
     */
    public Account insertAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        return null;
    }
}
