package Service;

import java.util.*;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }
    
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message addMessage(Message message) {
        if ((message.getMessage_text() == "") || (message.getMessage_text().length() > 255)) {
            return null;
        }
        return messageDAO.insertMessage(message);
    }

    public Message updateMessage(int message_id, Message message) {
        Message existingMessage = messageDAO.getMessageByID(message_id);
        if ((message == null) || (message.getMessage_text() == "") || (message.getMessage_text().length() > 255) || (existingMessage == null)) {
            return null;
        }
        existingMessage.setMessage_text(message.getMessage_text());
        return existingMessage;
    }

    public List<Message> getAllMessageByAccountID(int posted_by) {
        return messageDAO.getAllMessageByAccountID(posted_by);
    }

    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id);
    }

    public Message deleteMessageByID(int message_id) {
        Message deletedMessage = messageDAO.getMessageByID(message_id);
        if (deletedMessage.toString() == "") {
            return null;
        }
        return deletedMessage;
    }
}
