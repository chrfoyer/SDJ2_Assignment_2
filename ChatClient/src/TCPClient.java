import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient
{
  public static void main(String[] args) throws UnknownHostException, IOException {
    final int PORT = 5678;
    final String HOST = "localHost";

    Scanner input = new Scanner(System.in);

    Socket socket = new Socket(HOST, PORT);

    BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

    System.out.println("Sending connect to server");
    String request = "connect";
    System.out.println("Client> " + request);

    out.println(request);

    String reply = in.readLine();
    System.out.println("Server> " + reply);
    if (reply.equals("Disconnected")) {
      socket.close();
    }

    System.out.println("Enter username: ");
    request = input.nextLine();
    System.out.println("Client> " + request);

    out.println(request);

    reply = in.readLine();
    System.out.println("Server> " + reply);

    System.out.println("Enter password: ");
    request = input.nextLine();
    System.out.println("Client> " + request);

    out.println(request);

    reply = in.readLine();
    System.out.println("Server> " + reply);
    if (reply.equals("Approved")) {
      socket.close();
    }
  }
}