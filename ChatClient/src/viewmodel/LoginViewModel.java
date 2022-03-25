package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel
{
  private Model model;
  private StringProperty request;
  private StringProperty reply;
  private StringProperty error;

  public LoginViewModel(Model model)
  {
    this.model = model;
    error = new SimpleStringProperty();
    request = new SimpleStringProperty();
    reply = new SimpleStringProperty();
  }

  public void login()
  {
    try
    {
      //call setUsername on server
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

  public StringProperty requestProperty()
  {
    return request;
  }
}
