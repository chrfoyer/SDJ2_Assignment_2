package mediator;

import com.google.gson.Gson;

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
  private Gson gson;
  private String receivedString;

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

    gson = new Gson();
    ChatClientReceiver chatClientReceiver = new ChatClientReceiver(this, in);
    Thread thread = new Thread(chatClientReceiver);
    thread.start();

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
    // TODO: 2022. 03. 25.
    //placeholder values
    if (input.equals("ADD") || input.equals("GET") || input.equals("SIZE"))
    {
      System.out.println(input);
    }
    else
    {
      receivedString = input;
      notifyAll();
    }
  }
}
