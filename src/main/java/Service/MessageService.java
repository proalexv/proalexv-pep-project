package Service;

import static org.mockito.ArgumentMatchers.nullable;

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
     MessageDAO messageDAO = new MessageDAO();
    /**
     * no-args constructor for creating a new AccountService with a new AccountDAO.
     * There is no need to change this constructor.
     */
    public MessageService( MessageDAO messageDAO){
        this.messageDAO = messageDAO();
    }
    /**
     * Constructor for a AccountService when AccountDAO is provided.
     * This is used for when a mock AccountDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of AccountService independently of AccountDAO.
     * There is no need to modify this constructor.
     * @param messageDAO
     */
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
            return messageDAO.getMessageById(message_id):
        }
        return null;
    }return message;
    }
    public MEssage updateMessages(int mesage_id, Message message){
        if(messageDAO.getMessageById(message_id) != null)
        {
return messageDAO.UpdatebyId(message_id,message);
        }
        return null;

        }
    }