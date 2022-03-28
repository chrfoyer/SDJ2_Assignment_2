package mediator;

import com.google.gson.Gson;
import com.sun.webkit.Timer;
import model.Message;
import model.Model;
import model.ModelManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient implements PropertyChangeListener {
    private String host;
    private int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String reply;
    private Gson gson;
    private String receivedString;
    private Model model;

    public static final String HOST = "localhost";
    public static final int PORT = 6789;

    public ChatClient(String host, int port, Model model) {
        this.host = host;
        this.port = port;
        this.model = model;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        model.addListener(this);
        gson = new Gson();
        ChatClientReceiver chatClientReceiver = new ChatClientReceiver(this, in);
        Thread thread = new Thread(chatClientReceiver);
        thread.start();

    }

    public void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public void execute() {
        while (true) {
            if (!(receivedString == null)) {
//            System.out.println("Waiting for massage :)");
//            while (receivedString == null) {
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
                //we know we got something from the server
                Message received = gson.fromJson(receivedString, Message.class);
                if (received.getUserName().equals("Server")) {
                    System.out.println(received.toString());
                } else {
                    model.addMessage(received);
                }
                receivedString = null;
            }
        }
    }

        public synchronized void receive (String input){
            // TODO: 2022. 03. 25.
            //placeholder values
            if (input.equals("MESSAGE") || input.equals("SIZE")) {
                System.out.println(input);
            } else {
                receivedString = input;
                notifyAll();
            }
        }

        @Override
        public void propertyChange (PropertyChangeEvent evt){

            System.out.println("something happened");
            if (evt.getPropertyName().equals("NEW_MESSAGE")) {
                Message message = (Message) evt.getOldValue();
                String messageJson = gson.toJson(message, Message.class);
                out.println(messageJson);
            } else if (evt.getPropertyName().equals("SET_USERNAME")) {
                // Make the server send a broadcast when a user sets their username
                Message message = new Message((String) evt.getNewValue() + " connected", "Server");
                String messageJson = gson.toJson(message, Message.class);
                out.println(messageJson);
            }
            // Previous code
            // out.println(evt.getPropertyName());
            // out.println(evt.getOldValue());
        }
    }
