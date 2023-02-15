package DAO;

import java.util.List;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;


/**
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a
 * database. The methods here are mostly filled out, you will just need to add a SQL statement.
 *
 * We may assume that the database has already created a table named 'Message'
    /**
     * TODO: insert an account into the message table.
     * The message_id should be automatically generated by the sql database if it is not provided because it was
     * set to auto_increment. Therefore, you only need to insert a posted_by, message_text, time_posted_epoch
     * Change the sql String and leverage PreparedStatements' setString methods.

   public class MessageDAO{
     public Message CreateNewMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?)" ;

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here; BASE CODE HERE ONLY HAD 1 SET STRING
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(),message.getMessage_text(),message.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

	public Message addMessage(Message message) {
		return null;
	}
}

*/

public class MessageDAO{
    public Message InsertNewMessages(Message message){
    Connection connection = ConnectionUtil.getConnection();
    try{

   String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES(?,?,?)";
    
    
    PreparedStatement preparedStatment = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    
    preparedStatment.setInt(1,message.getPosted_by());
    preparedStatment.setString(2,message.getMessage_text());
    preparedStatment.setLong(3,message.getTime_posted_epoch());
    
    preparedStatment.executeUpdate();
    ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
    
    if(pkeyResultSet.next()){
    int generated_message_id = (int) pkeyResultSet.getLong(1);
    return new Message(generated_message_id,message.getPosted_by(),message.getMessage_text(),message.getTime_posted_epoch());}
    

    }
   catch(SQLException e){
    System.out.println(e.getMessage());
    }
    return null;
}
public List <Message>GetAllMessages(){
Connection connection = ConnectionUtil.getConnection();
List<Message> message = new ArraryList<>();
try{
    String sql = "SELECT * FROM message;";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet rs = preparedStatement.executeQuery();
    while(rs.next()){
        Message message = new Message(rs.getInt("message_id"),
        rs.getInt(sql)"posted_by"),
        rs.getString(sql)"message_text"),
        rs.getInt(sql)"time_posted"));
        message.add(message);
    }
}catch(SQLException e){
    System.out.println(e.getMessage());
}
return messages;

}
public Message getMessageById(int id){
Connection connection = COnnectionUtil.getConnection;
try{
    String sql = "SELECT * FROM message WHERE message_id =?;";
    preparedStatment.setInt(1,id);

ResultSet rs = preparedStatement.executeQuery();
while(rs.next()){
    Message message = new Message()
    rs.getInt("message_id"),
    rs.getInt("posted_by"),
    rs.getString("message_text"),
    rs.getInt("time_posted_epoch"));
    return message;
}
}
catch(SQLException e){
System.out.println(e.getMessage());
}
return null;
}


public Message DeleteMessaagebyId(int message_id){
    Connection Connection = ConnectionUtil.getConnection();
    try{
        String sql = "update message set message_text = ? where message_id = ?;";
        PreparedStatement preparedStatement = connection.preparedStatement(sql);

        preparedStatement.setString(1, message.getMessage_text());
        preparedStatement.setInt(2, message_id);
    
    preparedStatement.executeUpdate();
    return getMessageById(message_id);

}
catch(SQLException e)
{

    system.out.println(e.getMessage());
}
}
}
    
    