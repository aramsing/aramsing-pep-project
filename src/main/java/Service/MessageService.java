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
        return messageDAO.getAllMessages();
    }

    /*
     * Business logic to add a message
     * Go between from the back to front end
     */
    public Message addMessage(Message message) {
        if ((message.getMessage_text() == "") || (message.getMessage_text().length() > 255)) {
            return null;
        }
        return messageDAO.insertMessage(message);
    }

    /*
     * Business logic to update a message by its id
     * Go between from the back to front end
     */
    public Message updateMessageByID(int message_id, Message message) {
        Message existingMessage = messageDAO.getMessageByID(message_id);
        if ((existingMessage == null) || (message.getMessage_text() == "") || (message.getMessage_text().length() > 255) || (message == null)) {
            return null;
        }
        messageDAO.updateMessageByID(message_id, message);
        return messageDAO.getMessageByID(message_id);
    }

    /*
     * Business logic to get all messages by the user id
     * Go between from the back to front end
     */
    public List<Message> getAllMessageByAccountID(int posted_by) {
        return messageDAO.getAllMessageByAccountID(posted_by);
    }

    /*
     * Business logic to get a message by its id
     * Go between from the back to front end
     */
    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id);
    }

    /*
     * Business logic to delete a message by its id
     * Go between from the back to front end
     */
    public Message deleteMessageByID(int message_id) {
        Message deletedMessage = messageDAO.getMessageByID(message_id);
        if (deletedMessage == null) {
            return null;
        }
        messageDAO.deleteMessageByID(message_id);
        return deletedMessage;
    }
}
