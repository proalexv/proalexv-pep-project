package DAO;
import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;


public class AccountDAO{
    public Account registerAccount(Account account)
    {
    Connection connection = ConnectionUtil.getConnection();
    try
    {
    String sql = "INSERT INTO account (username, password) VALUES (?,?)";
    PreparedStatement preparedStatment = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    
    preparedStatment.setString(1,account.getUsername());
    preparedStatment.setString(2,account.getPassword());
    preparedStatment.executeUpdate();
    
    ResultSet rs =  preparedStatment.getGeneratedKeys();
    
    if(rs.next())
    {
    int generated_account_id = (int) rs.getLong(1);
    return new Account
    (
        generated_account_id,account.getUsername(),account.getPassword());
    }
}
catch(SQLException e)
{
    System.out.println(e.getMessage());
    }
    return null;
    }
    
public Account userLogin(String username, String password)
{
        Connection connection = ConnectionUtil.getConnection();
        try
        {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatment = connection.prepareStatement(sql);


            preparedStatment.setString(1,username);
            preparedStatment.setString(2,password);
           
            
            ResultSet rs= preparedStatment.executeQuery();
            while(rs.next())
            { 
                return new Account(
                rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password"));
            }
        } catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
                return null;
            }
        }
       

    



/** ignore me
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a
 * database. The methods here are mostly filled out, you will just need to add a SQL statement.
 *
 * We may assume that the database has already created a table named 'Account' (CAN WE ASSUME THIS?)
    /**
     * TODO: insert an account into the account table.
     * The account_id should be automatically generated by the sql database if it is not provided because it was
     * set to auto_increment. Therefore, you only need to insert a username and password.
     * Change the sql String and leverage PreparedStatements' setString methods.
*/
