package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageList
{
  private static MessageList instance;
  private ArrayList<Message> messages;
  private String key;
  private static Map<String, MessageList> map = new HashMap<>();

  private MessageList()
  {
    instance = new MessageList();
    messages = new ArrayList<>();
    key = instance.key;
  }

  public static MessageList getInstance(String key)
  {
    MessageList instance = map.get(key);
    if (instance == null)
    {
      synchronized (map)
      {
        instance = map.get(key);
        if (instance == null)
        {
          instance = new MessageList();
          map.put(key, instance);
        }
      }
    }
    return instance;
  }

  public void addMessage(String text, String userName)
  {
    Message line = new Message(text, userName);
    messages.add(line);
    addToFile(line);
  }

  public ArrayList<Message> getAll()
  {
    return messages;
  }

  public String toString()
  {
    String str = "";
    for (int i = 0; i < messages.size(); i++)
    {
      str += messages.get(i).toString();
    }
    return str;
  }

  private void addToFile(Message message)
  {
    if (message == null)
    {
      return;
    }
    BufferedWriter out = null;
    try
    {
      String filename = "Message-" + key+ ".txt";
      out = new BufferedWriter(new FileWriter(filename, true));
      for (Message temp:messages
           )
      {
        out.write(temp.toString()+"\n");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

  }

}