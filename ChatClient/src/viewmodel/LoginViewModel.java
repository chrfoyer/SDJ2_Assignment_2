package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Message;
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

  public boolean login()
  {

    try
    {
      if (username.get() == null)
      {
        throw new IllegalArgumentException("Can't use an empty username!");
      }
      else
      {
        model.setUsername(username.get());
        return true;
      }
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }
    return false;
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
