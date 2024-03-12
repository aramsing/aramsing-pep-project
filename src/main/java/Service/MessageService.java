package Service;

import java.util.*;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;

    /*
     * Constructor
     */
    public MessageService() {
        messageDAO = new MessageDAO();
    }
    
    /*
     * Constructor Overridden
     * Initializes specific instance of Account Service
     */
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    /*
     * Business logic to get all messages
     * Go between from the back to front end
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages(); // returns all messages from the database
    }

    /*
     * Business logic to add a message
     * Go between from the back to front end
     */
    public Message addMessage(Message message) {
        if ((message.getMessage_text() == "") || (message.getMessage_text().length() > 255)) { // if the message is blank or has more than 255 characters, return null
            return null;
        }
        return messageDAO.insertMessage(message); // return the newly created message from the database
    }

    /*
     * Business logic to update a message by its id
     * Go between from the back to front end
     */
    public Message updateMessageByID(int message_id, Message message) {
        Message existingMessage = messageDAO.getMessageByID(message_id); // get the message by its id
        if ((existingMessage == null) || (message.getMessage_text() == "") || (message.getMessage_text().length() > 255) || (message == null)) { // if the existing message is null, or the message is blank, or has more than 255 characters, or the message itself is null, return null
            return null;
        }
        messageDAO.updateMessageByID(message_id, message); // update the message in the database
        return messageDAO.getMessageByID(message_id); // return the new message by its id
    }

    /*
     * Business logic to get all messages by the user id
     * Go between from the back to front end
     */
    public List<Message> getAllMessageByAccountID(int posted_by) {
        return messageDAO.getAllMessageByAccountID(posted_by); // return all the messages created by a specific user from the database
    }

    /*
     * Business logic to get a message by its id
     * Go between from the back to front end
     */
    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id); // return a message by its id from the database
    }

    /*
     * Business logic to delete a message by its id
     * Go between from the back to front end
     */
    public Message deleteMessageByID(int message_id) {
        Message deletedMessage = messageDAO.getMessageByID(message_id); // get the message by its id
        if (deletedMessage == null) { // if the message id does not exist, return null
            return null;
        }
        messageDAO.deleteMessageByID(message_id); // remove the message from the database
        return deletedMessage; // return the message contents
    }
}
