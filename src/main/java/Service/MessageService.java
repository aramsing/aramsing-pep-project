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

    public boolean updateMessage(Message message) {
        boolean existingMessage = messageDAO.updateMessage(message);
        if ((existingMessage == false) || (message.getMessage_text().isEmpty()) || (message.getMessage_text().length() > 255)) {
            return false;
        }
        return existingMessage;
    }

    public List<Message> getAllMessageByAccountID(int posted_by) {
        return messageDAO.getAllMessageByAccountID(posted_by);
    }

    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id);
    }

    public boolean deleteMessageByID(Message message) {
        boolean deletedMessage = messageDAO.deleteMessage(message);
        if (deletedMessage == false) {
            return false;
        }
        else {
            return true;
        }
    }
}
