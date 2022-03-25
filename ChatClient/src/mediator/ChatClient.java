package mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient implements RemoteModel
{
  private String host;
  private int port;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private String reply;

  public static final String HOST = "localhost";
  public static final int PORT = 6789;

  public ChatClient(String host, int port)
  {
    this.host = host;
    this.port = port;
  }

  @Override public void connect() throws IOException
  {
    socket = new Socket(host, port);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);

  }

  @Override public void disconnect() throws IOException
  {
    in.close();
    out.close();
    socket.close();
  }

  /*
  @Override synchronized public String convert(String source) throws IOException
  {
    out.println(source);
    try
    {
      wait();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    return reply;
  }

   */

  public synchronized void receive(String input)
  {
    if (input.startsWith("Message "))
    {
      reply = input;
      notify();
    }
    else
    {
      //fire an event
    }
  }
}
