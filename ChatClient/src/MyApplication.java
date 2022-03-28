import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatClient;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    try
    {
      Model model = new ModelManager();

      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);

      view.start(primaryStage);
      ChatClient client = new ChatClient("localhost", 9876, model);
      new Thread(client).start();
      client.connect();
      // client.execute();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
