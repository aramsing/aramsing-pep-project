package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.*;
import Service.*;
import java.util.List;

/**
 * You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases.
 * You should refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    Account account;
    MessageService messageService;
    Message message;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.account = new Account();
        this.messageService = new MessageService();
        this.message = new Message();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterHandler); //done
        app.post("/login", this::postLogInHandler); //done
        app.get("/messages", this::getAllMessagesHandler); //done
        app.post("/messages", this::postAddMessageHandler); //done
        app.patch("/messages/{message_id}", this::patchUpdateMessageHandler); //done
        app.delete("/messages/{message_id}", this::deleteMessageHandler); //done
        app.get("/messages/{message_id}", this::getIndividualMessageHandler); //done
        app.get("/accounts/{account_id}/messages", this::getAllMessagesFromAnAccount); //done
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesHandler(Context context) throws JsonProcessingException {
        List<Message> messages = messageService.getAllMessages();
        context.status(200).json(messages);
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postAddMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if (addedMessage != null) {
            context.status(200).json(addedMessage);
        }
        else {
            context.status(400);
        }
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void patchUpdateMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessageByID(message_id, message);
        if (updatedMessage != null) {
            context.status(200).json(updatedMessage);
        }
        else {
            context.status(400);
        }
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postRegisterHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount != null) {
            context.status(200).json(addedAccount);
        }
        else {
            context.status(400);
        }
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postLogInHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account loginAccount = mapper.readValue(context.body(), Account.class);
        Account loggedInAccount = accountService.logInAccount(loginAccount);
        if (loggedInAccount != null) {
            context.status(200).json(loggedInAccount);
        }
        else {
            context.status(401);
        }
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void deleteMessageHandler(Context context) throws JsonProcessingException {
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessageByID(message_id);
        if (deletedMessage != null) {
            context.status(200).json(deletedMessage);
        }
        else {
            context.status(200);
        }
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getIndividualMessageHandler(Context context) throws JsonProcessingException {
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.getMessageByID(message_id);
        if (message != null) {
            context.status(200).json(message);
        } 
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesFromAnAccount(Context context) throws JsonProcessingException {
        int account_id = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messages = messageService.getAllMessageByAccountID(account_id);
        context.status(200).json(messages); 
    }
}