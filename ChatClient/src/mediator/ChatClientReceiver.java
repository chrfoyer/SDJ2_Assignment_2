package mediator;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReceiver implements Runnable
{
  private ChatClient client;
  private BufferedReader in;

  public ChatClientReceiver(ChatClient client, BufferedReader in)
  {
    this.client = client;
    this.in = in;
  }

  @Override public void run()
  {
    while (true)
    {
      try
      {
        String input = in.readLine();

        client.receive(input);

      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
