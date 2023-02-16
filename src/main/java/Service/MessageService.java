package Service;

import static org.mockito.ArgumentMatchers.nullable;
import Model.Message;
import java.util.List;

import DAO.MessageDAO;
import Model.Message;
import DAO.MessageDAO;



public class MessageService {
    private MessageDAO messageDAO;
    
    public MessageService(){
        messageDAO = new MessageDAO();
    }
        public MessageService(MessageDAO messageDAO)
        {
            this.messageDAO = messageDAO;
        }
   
    public List<Message>getAllMessage(){
return messageDAO.getAllMessages();
    }


    public Message createMessage(Message message){
        String message_text = message.getMessage_text();
        //
        if(!message_text.isBlank() && message_text.length() < 255){
            return messageDAO.createMessage(message);
        } else{
            return null;
        }
    }
    public Message getMessageByID(int message_id){
        return messageDAO.getMessageById(message_id);
    } 
    
    public Message deleteMessageById(int message_id){
        Message messageFromDB = this.messageDAO.getMessageById(message_id);
        messageDAO.deleteMessaagebyId(message_id);
        if(messageFromDB==null){
            return null;
        }return messageFromDB;
    }

public Message updateMessageByID(Message message,int message_id){
    if(messageDAO.getMessageById(message_id) != null)
    {
        //return messageDAO.updateMessageByID(message, message_id);

   }
   return null;
}

public List<Message> getMessagesByAccountID(int posted_by){
    return messageDAO.getMessagesByAccountID(posted_by);
}

}   