package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    messages = FXCollections.observableArrayList();
    ;
    textInput = new SimpleStringProperty();
  }

  // TODO: 2022. 03. 25.

  public void reset()
  {
    //put in everything from model.getAllmessages() from server
    error.set("");
    textInput.set("");

  }

  public void sendMessage()
  {
    Message input = new Message(textInput.get(), model.getUsername());
    model.addMessage(input);
    reset();
  }

  public StringProperty errorProperty()
  {
    return error;
  }

  public ObservableList<String> messagesProperty()
  {
    return messages;
  }

  public StringProperty textInputProperty()
  {
    return textInput;
  }

}
