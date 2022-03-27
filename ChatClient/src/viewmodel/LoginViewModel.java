package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class LoginViewModel
{
  private Model model;
  private StringProperty username;
  private StringProperty error;

  public LoginViewModel(Model model)
  {
    this.model = model;
    error = new SimpleStringProperty();
    username = new SimpleStringProperty();
  }

  public void login()
  {
    try
    {
      // TODO: 2022. 03. 25.
      model.setUsername(username.get());
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }

  public StringProperty errorProperty()
  {
    return error;
  }

  public StringProperty usernameProperty()
  {
    return username;
  }
}
