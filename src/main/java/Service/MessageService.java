package Service;

import static org.mockito.ArgumentMatchers.nullable;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

/**
 * The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
 * persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web or
 * SQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
 * actions undertaken by the API to a logging file.
 *
 * It's perfectly normal to have Service methods that only contain a single line that calls a DAO method. An
 * application that follows best practices will often have unnecessary code, but this makes the code more
 * readable and maintainable in the long run!
 */

public class MessageService {
    public MessageDAO messageDAO;
    
    public MessageService(){
        messageDAO = new MessageDAO();
    }
        public MessageService(MessageDAO messageDAO)
        {
            this.messageDAO = messageDAO;
        }
    public Message InsertNewMessage(Message message){
    if (message.getMessage_text() ==""){
        return null;
    }
    return messageDAO.InsertNewMessages(message);
    }
    public List<Message>getAllMessage(){
return messageDAO.GetAllMessages();
    }
    public Message getMessageById(int message_id){
        if(messageDAO.getMessageById(message_id)!= null){
            return messageDAO.getMessageById(message_id);
        }
        return null;
    }
    public Message deletebyid(int message_id){
        Message message = messageDAO.getMessageById(message_id);
        messageDAO.DeleteMessaagebyId(message_id);
        if(message ==null){
        return null;
    } 
    return message;
}

public Message updateMessages(int message_id,Message message){
    if(messageDAO.getMessageById(message_id) != null)
    {
        return messageDAO.UpdatebyId(message_id,message);
 
   }
   return null;
}
}   