package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.*;
import Service.*;
import java.util.List;

/**
 * This is the front end of the project
 * This class uses various http addresses and methods to create functionality to the database and DAO operations via a service layer
 */
public class SocialMediaController {

    // Create objects and construct the current reference of objects
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
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLogInHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.post("/messages", this::postAddMessageHandler);
        app.patch("/messages/{message_id}", this::patchUpdateMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.get("/messages/{message_id}", this::getIndividualMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesFromAnAccount);
        return app;
    }

    /**
     * This method returns all messages from our database.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesHandler(Context context) throws JsonProcessingException {
        List<Message> messages = messageService.getAllMessages(); // grabs all messages existing in the database and puts them in the list
        if (messages != null) { // if all messages are found
            context.status(200).json(messages); // return OK and display the messages
        }
        else {
            context.status(200); // return OK
        }
    }

    /**
     * This method creates a new message in our database.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postAddMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class); // read the body of the message through its template
        Message addedMessage = messageService.addMessage(message); // add the message
        if (addedMessage != null) { // if the message is successfully created
            context.status(200).json(addedMessage); // return OK and display the new message
        }
        else {
            context.status(400); // return client error
        }
    }

    /**
     * This method updates a message in our database.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void patchUpdateMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class); // read the body of the message through its template
        int message_id = Integer.parseInt(context.pathParam("message_id")); // get the id of the message through its json format
        Message updatedMessage = messageService.updateMessageByID(message_id, message); // update the message
        if (updatedMessage != null) { // if the message is successfully updated
            context.status(200).json(updatedMessage); // return OK and display the new message
        }
        else {
            context.status(400); // return client error
        }
    }

    /**
     * This method creates a new account in our database.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postRegisterHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class); // read the body of the account through its template
        Account addedAccount = accountService.addAccount(account); // add the account
        if(addedAccount != null) { // if the account is successfully created
            context.status(200).json(addedAccount); // return OK and display the new account
        }
        else {
            context.status(400); // return client error
        }
    }

    /**
     * This method logs into an account that is in our database.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postLogInHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account loginAccount = mapper.readValue(context.body(), Account.class); // read the body of the account through its template
        Account loggedInAccount = accountService.logInAccount(loginAccount); // log in to the account
        if (loggedInAccount != null) { // if the user successfully logs into an account
            context.status(200).json(loggedInAccount); // return OK and display the correct account
        }
        else {
            context.status(401); // return unauthorized
        }
    }

    /**
     * This method deletes a message from our database.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void deleteMessageHandler(Context context) throws JsonProcessingException {
        int message_id = Integer.parseInt(context.pathParam("message_id")); // get the id of the message through its json format
        Message deletedMessage = messageService.deleteMessageByID(message_id); // delete the message
        if (deletedMessage != null) { // if the deleted message is not null
            context.status(200).json(deletedMessage); // return OK and dislay the deleted message
        }
        else {
            context.status(200); // return OK
        }
    }

    /**
     * This method creates a new message in our database.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getIndividualMessageHandler(Context context) throws JsonProcessingException {
        int message_id = Integer.parseInt(context.pathParam("message_id")); // get the id of the message through its json format
        Message message = messageService.getMessageByID(message_id); // grabs the message from the database
        if (message != null) { // if the message is found in the database
            context.status(200).json(message); // return OK and display message
        }
        else {
            context.status(200); // return OK
        }
    }

    /**
     * This method creates a new message in our database.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesFromAnAccount(Context context) throws JsonProcessingException {
        int account_id = Integer.parseInt(context.pathParam("account_id")); // get the id of the message through its json format
        List<Message> messages = messageService.getAllMessageByAccountID(account_id); // grabs the messages by its creators id from the database and puts them in the list
        if (messages != null) { // if the messages are found in the database
            context.status(200).json(messages); // return OK and display the messages
        }
        else {
            context.status(200); // return OK
        }
    }
}