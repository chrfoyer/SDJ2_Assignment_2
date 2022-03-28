package model;

import java.time.LocalDateTime;

public class Message
{
  private String userName;
  private LocalDateTime timeStamp;
  private String message;

  public Message(String message, String userName)
  {
    this.message = message;
    this.userName = userName;
    timeStamp = LocalDateTime.now();
  }

  @Override public String toString()
  {
    return userName + " -> " + message;
  }

  public String getMessage()
  {
    return message;
  }

  public String getUserName() {
    return userName;
  }
}
