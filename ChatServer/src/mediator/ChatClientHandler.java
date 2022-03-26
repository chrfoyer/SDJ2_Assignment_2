package mediator;

import com.google.gson.Gson;
import model.Message;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class ChatClientHandler implements PropertyChangeListener, Runnable
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private Gson gson;
  private Model model;

  public ChatClientHandler(Socket socket, Model model) throws IOException
  {
    this.socket = socket;
    this.model = model;
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    running = true;
    gson = new Gson();
    model.addListener(this);
  }

  public void stop() throws IOException
  {
    in.close();
    out.close();
    running = false;
  }

  @Override public void run()
  {
    while (running)
    {
      try
      {
        // Read in json from client and convert to message
        String request = in.readLine();
        System.out.println("Client> " + request);
        Message message = gson.fromJson(request, Message.class);
        model.addMessage(message);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  // todo create way to count clients currently connected

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    // By listening for the property changes here, we can broadcast the
    // messages to all the clients connected
    if (evt.getPropertyName().equals("NEW_MESSAGE"))
    {
      // A new message is added to the model
      //         New value: username       Old value: message string
      out.println(evt.getNewValue() + "> " + evt.getOldValue());
      System.out.println(
          "Server broadcast> " + evt.getNewValue() + "> " + evt.getOldValue());
    }
  }
}
