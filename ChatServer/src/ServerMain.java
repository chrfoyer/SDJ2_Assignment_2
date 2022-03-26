import mediator.ChatClientConnector;
import model.*;

import java.io.IOException;

public class ServerMain
{
  public static void main(String[] args)
  {
    try
    {
      new Thread(new ChatClientConnector(new ModelManager())).start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
