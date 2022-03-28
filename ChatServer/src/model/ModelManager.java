package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;

public class ModelManager implements Model {

    private PropertyChangeSupport property = new PropertyChangeSupport(this);
    private String username;
    private MessageList messageList;
    private int numberOfConnectedUsers;

    public ModelManager() {
        initializeChat();
    }

    @Override
    public void setUsername(String userName) {
        this.username = userName;
        property.firePropertyChange("SET_USERNAME", true, username);
    }

    @Override
    public void initializeChat() {
        messageList = MessageList.getInstance(
                LocalDateTime.now().getDayOfMonth() + "");
    }

    @Override
    public MessageList getAllMessagesForDay(String day) {
        return MessageList.getInstance(day);
    }

    @Override
    public void addMessage(String message) {
        messageList.addMessage((new Message(message, username)));
        property.firePropertyChange("NEW_MESSAGE", message, username);
    }

    @Override
    public void addMessage(Message message) {
        messageList.addMessage(message);
        property.firePropertyChange("NEW_MESSAGE", message,
                message.getUserName());
    }

    @Override
    public int getNumberOfConnectedUsers() {
        return numberOfConnectedUsers;
    }

    @Override
    public void setNumberOfConnectedUsers(int numberOfConnectedUsers) {
        this.numberOfConnectedUsers = numberOfConnectedUsers;
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
