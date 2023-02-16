package DAO;
import java.util.ArrayList;
import java.util.List;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;

public class MessageDAO{
    public List<Message> getAllMessages()
    {
    Connection connection = ConnectionUtil.getConnection();
    List<Message> messages = new ArrayList<>();

    try
    {

   String sql = "SELECT * FROM message";
    
    
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
   ResultSet rs = preparedStatement.executeQuery();
   while(rs.next())
   {
    Message message = new Message(
    rs.getInt("message_id"),
    rs.getInt("posted_by"),
    rs.getString("message_text"),
    rs.getInt("time_posted_epoch"));
    messages.add(message);
   }
}
catch(SQLException e){
System.out.println(e.getMessage());
}
return messages;
    }

    public Message createMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO message (posted_by,message_text,time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,message.getPosted_by());
            preparedStatement.setString(2,message.getMessage_text());
            preparedStatement.setLong(3,message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            {
                
                
                int generated_message_id = (int) rs.getLong(1);
                return new Message(
                generated_message_id,
                message.getPosted_by(),
                message.getMessage_text(),
                message.getTime_posted_epoch());

            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
    }
 
    return null;
    }

    public Message getMessageById(int id)
    {
        Connection connection = ConnectionUtil.getConnection();
        try
        {
            String sql = "SELECT * FROM message WHERE message_id =?;";
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
        
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next())
        {
           return new Message(
            rs.getInt("message_id"),
            rs.getInt("posted_by"),
            rs.getString("message_text"),
            rs.getInt("time_posted_epoch"));
        }
        }
        catch(SQLException e){
        System.out.println(e.getMessage());
        }
        
        return null;
        }


        public Message deleteMessaagebyId(int message_id)
        {
            Connection Connection = ConnectionUtil.getConnection();
            try
            {
                String sql = "DELETE FROM message where message_id = ?;";
                

            
            
            PreparedStatement preparedStatement = Connection.prepareStatement(sql);
            
            
            
            preparedStatement.setInt(1, message_id);
            
            
            
            
            preparedStatement.executeUpdate();
            return getMessageById(message_id);
        
        }
        catch(SQLException e)
        {
        
            System.out.println(e.getMessage());
        }return null;
        }


        public Message updateMessageById(Message message,int message_id)
        {
            Connection connection = ConnectionUtil.getConnection();
            try
            {
                String sql = "UPDATE message SET message_text =? WHERE message_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
                preparedStatement.setString(1, message.getMessage_text());
                preparedStatement.setInt(2,message_id);

                preparedStatement.executeUpdate();
                return getMessageById(message_id);
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return null;

        }





        public List<Message> getMessagesByAccountID(int posted_by)
        {
            Connection connection = ConnectionUtil.getConnection();
            List<Message> messages = new ArrayList<>();
            try
            {
                String sql = "SELECT * FROM message where posted_by =?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, posted_by);

                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next())
                {
                    Message message = new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getInt("time_posted_epoch"));
                        messages.add(message);
                }
                }catch(SQLException e){
                    System.out.println(e.getMessage());

                }
                return messages;
            }


        }

        
        
        
        











    /** ignore me
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a
 * database. The methods here are mostly filled out, you will just need to add a SQL statement.
 *
 * We may assume that the database has already created a table named 'Message'
    /**
     * TODO: insert an account into the message table.
     * The message_id should be automatically generated by the sql database if it is not provided because it was
     * set to auto_increment. Therefore, you only need to insert a posted_by, message_text, time_posted_epoch
     * Change the sql String and leverage PreparedStatements' setString methods.

*/