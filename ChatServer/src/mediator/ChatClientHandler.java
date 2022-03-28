package mediator;

import com.google.gson.Gson;
import model.Message;
import model.MessageList;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.time.LocalDateTime;

public class ChatClientHandler implements PropertyChangeListener, Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Gson gson;
    private Model model;

    public ChatClientHandler(Socket socket, Model model) throws IOException {
        this.socket = socket;
        this.model = model;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        running = true;
        gson = new Gson();
        this.model.addListener(this);
        // Increment the number of connected users in the model
        this.model.setNumberOfConnectedUsers(model.getNumberOfConnectedUsers() + 1);
        String messageJson = gson.toJson(new Message("Welcome", "Server"), Message.class);
        out.println(messageJson);
        System.out.println("Server> Client connected");
    }

    public void stop() throws IOException {
        // todo call this when a client wishes to disconnect
        in.close();
        out.close();
        running = false;
        // Decrement the number of connected users in the model
        model.setNumberOfConnectedUsers(model.getNumberOfConnectedUsers() - 1);
    }

    @Override
    public void run() {
        while (running) {
            try {
                System.out.println("Thread started");
                // Read in json from client and convert to message
                String request = in.readLine();
                System.out.println("Client> " + request);
                Message message = gson.fromJson(request, Message.class);
                // return number of connected users directly if the message contains "/count"
                if (message.getMessage().contains("/count")) {
                    out.println(
                            "Server to " + message.getUserName() + "> There are currently "
                                    + model.getNumberOfConnectedUsers() + " connected users");
                } else {
                    model.addMessage(message);
                }

                //sync todays messageList
                //String messageListJson = gson.toJson(model.getAllMessagesForDay(LocalDateTime.now().getDayOfMonth() + ""), MessageList.class);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // By listening for the property changes here, we can broadcast the
        // messages to all the clients connected
        if (evt.getPropertyName().equals("NEW_MESSAGE")) {
            // A new message is added to the model
            out.println("MESSAGE");
            Message message = (Message) evt.getOldValue();
            String messyJson = gson.toJson(message, Message.class);
            out.println(messyJson);
            System.out.println("Server> " + message.getMessage());
            //         New value: username       Old value: message string
//            out.println(evt.getNewValue() + "> " + evt.getOldValue());
//            System.out.println(
//                    "Server broadcast> " + evt.getNewValue() + "> " + evt.getOldValue());
        }
    }
}
