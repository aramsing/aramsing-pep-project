package Service;

import java.util.*;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
    }
    
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message addMessage(Message message) {
        try {
            Message existingMessage = messageDAO.insertMessage(message);
            if (existingMessage != null) {
                return null;
            }
            return message;
        } catch (Exception e) {
            return null;
        }
    }

    public Message updateMessage(int message_id, Message message) {
        try {
            Message existingMessage = messageDAO.getMessageByID(message_id);
            if (existingMessage == null) {
                return null;
            }
            existingMessage.setMessage_text(message.getMessage_text());
            existingMessage.setTime_posted_epoch(message.getTime_posted_epoch());
            return existingMessage;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Message> getAllMessageByAccountID(int posted_by) {
        return messageDAO.getAllMessageByAccountID(posted_by);
    }

    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id);
    }

    public Message deleteMessageByID(int message_id) {
        try {
            Message existingMessage = messageDAO.getMessageByID(message_id);
            if(existingMessage == null) {
                return null;
            }
            messageDAO.deleteMessage(message_id);
            return existingMessage;
        } catch (Exception e) {
            return null;
        }
    }
}
