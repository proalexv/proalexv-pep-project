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


public class SocialMediaController{
    AccountService accountService;
    MessageService messageService;
    
    public SocialMediaController(){
        this.accountService = new AccountService();
       this.messageService = new MessageService();
    }
    public Javalin startAPI(){
        Javalin app = Javalin.create();
        app.post("/register" ,this::PostRegisterHandler);
        app.post("/login",this::PostLoginHandler );
        app.post("/messages" ,this::PostNewMessageHandler);
        app.post("/messages/{message_id}" ,this::GetMessagebyIDHandler);
        app.delete("/messages/{message_id}",this::DeleteMEssagebyIDHandler);
        app.patch("messages/{message_id}",this::UpdatebyIDHandler);
        return app;
    }
    private void PostRegisterHandler (Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account Account = mapper.readValue(ctx.body(), Account.class);
         Account newAccount = accountService.addAccount(Account);
         if (newAccount != null){
         ctx.json(mapper.writeValueAsString(newAccount));
         ctx.status(200);
        }else{
         ctx.status(400);
    }
    
    }
    private void PostLoginHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(),Account.class);
        Account newlogin = accountService.PostLogins(account.getUsername(),account.getPassword());
        System.out.println(newlogin);
        if (newlogin != null){
        ctx.json(om.writeValueAsString(newlogin));
        ctx.status(200);
     
        }else{
    ctx.status(401);
    }
    }
    private void PostNewMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message listmessage = messageService.getMessageById(message_id);
        if(listmessage!=null){
        ctx.json(listmessage);
    }
    }
    public void DeleteMEssagebyIDHandler(Context ctx) throws JsonProcessingException{
        int message_id = Integer.parseInt(ctx.pathParm("message_id"));
        if(message!=null){
        ctx.json(message);
        }else{
        ctx.status(200);
    }
    }
    public void GetAllMessagesHandler (Context ctx) throws JsonProcessingException{
        List<Message> messages = messageService.getAllMessage();
        ctx.json(messages);

    }
    public void GetMessagebyIDHandler(Context ctx) throws JsonProcessingException{

        ObjectMapper om = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message listmessage = messageService.getMessageById(message_id);
            if(listmessage != null){
            ctx.json(listmessage);
        }
        }

    public void DeleteMessagebyIDHandler(Context ctx) throws DJsonProcessingException{

            //ObjectMapper om = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.deletebyid(message_id);
            if(message!= null){
            ctx.json(message);
         }else{
             ctx.status(200);
         }
        }

    public void GetMessagesbyAccountIdHandler(context ctx) throws JsonProcessingException{
        int posted_by = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message>messages=messageService.GetMessagesbyAccountId(posted_by);
        ctx.json(messages);
    }



    public void UpdatebyIDHandler(context ctx) throws JsonProcessingException{
         ObjectMapper mapper = new ObjectMapper();
         Message message = mapper.readValue(ctx.body(),Message.class);
         int updatedMessage = Integer.parseInt(ctx.pathParm("message_id"));
         Message existingMessage = messageService.updateMessages(updatedMessage,message);
        if(existingMessage ==null || existingMessage.message_text.isBlank()){
        ctx.status(400);
        }
        else{
        ctx.json(existingMessage);
        }
        }
        }
    










        /* ignore me
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 
public class SocialMediaController {
    AccountService accountService;
    
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
   
     public SocialMediaController(){
        accountService = new AccountService();
     }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        //app.get("example-endpoint", this::exampleHandler);
       
        app.post("/register", this::postAccountHandler);
        app.post("/messages", this::postMessagesHandler);
        app.post("/login", this::postLoginHandler);
        return app;
    }

    private void postAccountHandler(Context ctx) throws JsonProcessingException 
    {
       // ObjectMapper mapper = new ObjectMapper();
        //context.json("sample text");

        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        if(account!=null){
            ctx.status(400);
        }else{
            ctx.json(mapper.writeValueAsString(account));
        }
    }

    private void postMessagesHandler(Context ctx) throws JsonProcessingException 
    {
        // ObjectMapper mapper = new ObjectMapper();
         //context.json("sample text");
 
         ObjectMapper mapper = new ObjectMapper();
         Message message = mapper.readValue(ctx.body(), Message.class);
        if(message != null){
             ctx.status(400);
         }else{
             ctx.json(mapper.writeValueAsString(message));
         }
     }
    
     //COME Back to the login handler
     private void postLoginHandler(Context ctx) throws JsonProcessingException 
     {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
         if(message != null){
            ctx.json(mapper.writeValueAsString(message));
          }else{
              ctx.status(400);
          }
      }
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

}
*/