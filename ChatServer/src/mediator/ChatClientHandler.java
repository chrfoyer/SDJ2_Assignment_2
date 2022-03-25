package mediator;

import com.google.gson.Gson;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class ChatClientHandler implements PropertyChangeListener, Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Gson gson;
    private Model model;

    public ChatClientHandler(Socket socket, Model model) throws IOException {
        this.socket  = socket;
        this.model = model;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
        running = true;
        gson = new Gson();
    }

    public void stop() throws IOException{
        in.close();
        out.close();
        running = false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // todo property change
    }

    @Override
    public void run() {
        // todo server run: logic of server
    }
}
