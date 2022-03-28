package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginViewController extends ViewController
{
  @FXML private TextField inputField;
  @FXML private Label errorLabel;
  @Override protected void init()
  {
    errorLabel.textProperty().bind(getViewModelFactory().getLoginViewModel()
        .errorProperty());
    inputField.textProperty().bindBidirectional(getViewModelFactory().getLoginViewModel().usernameProperty());
  }

  public void login()
  {
    if (getViewModelFactory().getLoginViewModel().login())
    {
      getViewHandler().openView("ChatView.fxml");
    }

  }

    public void onEnter() {
    login();
    }
}
