import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// This is a class from an old exercise to help show how TCP works

public class TCPServer
{
  public static void main(String[] args) throws IOException
  {
    final int PORT = 5678;
    System.out.println("Starting Server");

    //create welcoming socket at port 5678
    ServerSocket welcomeSocket = new ServerSocket(PORT);

    while (true)
    {
      System.out.println("Waiting for a client...");

      //wait for contact by client
      Socket socket = welcomeSocket.accept();

      //create input stream attached to the socket
      BufferedReader in = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));

      //create output stream attached to the socket
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      String request = in.readLine(); //read line from client



      if (!request.equals("connect"))
      {
        out.println("Disconnected");
        socket.close();
      } else {

        String reply = "Username?";
        System.out.println("Server> " + reply);
        out.println(reply);
        request = in.readLine();
        System.out.println("Client> " + request);

        reply = "Password?";
        System.out.println("Server> " + reply);
        out.println(reply);
        request = in.readLine();
        System.out.println("Client> " + request);

        System.out.println("Server> " + "Approved");
        out.println("Approved");
        break;
      }

      //   System.out.println("Client> " + request);
      //    String reply = request.toUpperCase();

    }

    //   out.println(reply);//Send line to client

    //loop back and wait for another client connection
  }
}
