package model;

public interface Model
{
  public void setUsername(String userName);

  public void initializeChat();

  public MessageList getAllMessagesForDay(String day);
  public void addMessage(String message);

}
