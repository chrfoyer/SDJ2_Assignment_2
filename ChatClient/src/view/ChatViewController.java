package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatViewController extends ViewController
{
  @FXML private Label errorLabel;
  @FXML private TextField inputField;
  @FXML private ListView<String> listView;
  @Override protected void init()
  {
    errorLabel.textProperty().bind(getViewModelFactory().getChatViewModel()
        .errorProperty());
    inputField.textProperty().bindBidirectional(getViewModelFactory().getChatViewModel()
        .textInputProperty());
    listView.setItems(getViewModelFactory().getChatViewModel().messagesProperty());
  }

  public void logout()
  {
    getViewHandler().openView("LoginView.fxml");
  }

  public void send()
  {
    //
  }
}
