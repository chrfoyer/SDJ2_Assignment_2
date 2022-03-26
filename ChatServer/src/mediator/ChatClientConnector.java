package mediator;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatClientConnector implements Runnable {
    private final int PORT = 9876;
    private boolean running;
    private ServerSocket welcomeSocket;
    private Model model;

    public ChatClientConnector(Model model) throws IOException {
        this.model = model;
        welcomeSocket = new ServerSocket(PORT);
        running = true;
        System.out.println("Server started on port " + PORT);
    }

    @Override
    public void run() {
        while (true) {
            try {
                new Thread(new ChatClientHandler(welcomeSocket.accept(), model)).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Client failed to connect");
            }
        }

    }
}
