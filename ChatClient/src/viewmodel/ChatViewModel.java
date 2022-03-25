package viewmodel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import model.Model;

public class ChatViewModel
{
  private Model model;
  private ObservableList<String> messages;
  private StringProperty textInput;
  private StringProperty error;

  public ChatViewModel(Model model)
  {
    this.model = model;
    error = new SimpleStringProperty();
    messages = new SimpleListProperty<>();
    textInput = new SimpleStringProperty();
  }

  // TODO: 2022. 03. 25.

  public void reset()
  {
    //put in everything from model.getAllmessages() from server
    error.set("");
    textInput.set("");

  }

  //method that adds shit on server

  public StringProperty errorProperty()
  {
    return error;
  }

  public ObservableList messagesProperty()
  {
    return messages;
  }

  public StringProperty textInputProperty()
  {
    return textInput;
  }

}
