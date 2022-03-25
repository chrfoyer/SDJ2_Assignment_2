package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ModelManager implements Model
{

  private PropertyChangeSupport property;
  private String username;
  private MessageList messageList;

  @Override public void setUsername(String userName)
  {
    this.username = userName;
    property.firePropertyChange("SET_USERNAME", true, username);

  }

  @Override public void initializeChat()
  {
    messageList = MessageList.getInstance(
        LocalDateTime.now().getDayOfMonth() + "");
  }

  @Override public MessageList getAllMessagesForDay(String day)
  {
    return MessageList.getInstance(day);
  }

  @Override public void addMessage(String message)
  {
    messageList.addMessage(message, username);
    property.firePropertyChange("NEW_MESSAGE", message, username);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
