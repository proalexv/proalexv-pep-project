package Controller;
import Model.Account;
import Model.Message; 
import Service.AccountService;
import Service.MessageService;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

//testing for save


public class SocialMediaController
{
    AccountService accountService;
    MessageService messageService;
    
       public SocialMediaController()
       {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public Javalin startAPI()
    {
        Javalin app = Javalin.create();
        app.post("register" ,this::postRegisterHandler);
        app.post("login", this::postUserLoginHandler);
        app.post("messages", this::postCreatedMessagesHandler);
        
        app.get("messages",this::getMessageHandler);
        app.get("messages/{message_id}",this::getMessageByIDHandler);
        
        app.delete("messages/{message_id}",this::deletetMessageByIDHandler);
        
        app.patch("messages/{message_id}",this::updateMessageByIDHandler);
        
        app.get("accounts/{account_id}/messages",this::getMessagesByIDHandler);
        return app;
    }

    private void postRegisterHandler (Context ctx) throws JsonProcessingException
    {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
         Account addAccount = accountService.addAccount(account);
         if (addAccount != null){
         ctx.json(om.writeValueAsString(addAccount));
         ctx.status(200);
        }
        else
        {
         ctx.status(400);
        }
    }

    private void postUserLoginHandler(Context ctx) throws JsonProcessingException
    {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(),Account.class);
        Account userLogIn = accountService.logIn(account.getUsername(),account.getPassword());
        
        if (userLogIn != null){
        ctx.json(om.writeValueAsString(userLogIn));
        }
        else
        {
    ctx.status(401);
        }
    }

    private void postCreatedMessagesHandler(Context ctx) throws JsonProcessingException
    {
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(ctx.body(),Message.class);
        Message createdMessage = messageService.createMessage(message);
        if(createdMessage != null)
        {
            ctx.json(om.writeValueAsString(createdMessage));
            ctx.status(200);
        } 
        else
        { ctx.status(400);
        }
    }


public void getMessageHandler(Context ctx)
{
    List<Message> messages = messageService.getAllMessage();
    ctx.json(messages);
}


    public void getMessageByIDHandler(Context ctx) throws JsonProcessingException
    {

        ObjectMapper om = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message messagebyID = messageService.getMessageByID(message_id);
            if(messagebyID != null)
            {
            ctx.result(om.writeValueAsString(messagebyID));
            ctx.status(200);
        }
        else
        {
            ctx.status(401);
        }
        }

   

    
    
    public void deletetMessageByIDHandler(Context ctx) throws JsonProcessingException
    {

            //ObjectMapper om = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message deleteMessage = messageService.deleteMessageById(message_id);
            if(deleteMessage!= null){
            ctx.json(deleteMessage);
         }
         else
         {
             ctx.status(200);
         }
        }

        public void updateMessageByIDHandler(Context ctx) throws JsonProcessingException
        {
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.readValue(ctx.body(),Message.class);
            int message_id = Integer.parseInt(ctx.pathParam("message_id"));
            Message updatedMessage = messageService.updateMessageByID(message,message_id);
           if(updatedMessage ==null || updatedMessage.message_text.isBlank())
           {
           ctx.status(400);
           }
           else{
           ctx.json(updatedMessage);
           }
           }

    public void getMessagesByIDHandler(Context ctx) throws JsonProcessingException
    {
        int posted_by = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages=messageService.getMessagesByAccountID(posted_by);
        ctx.json(messages);
    }

        }
    
