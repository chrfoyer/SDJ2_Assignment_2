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
import java.time.LocalDateTime;

public class ChatViewModel implements PropertyChangeListener
{
  private Model model;
  private ObservableList<String> messages;
  private StringProperty textInput;
  private StringProperty error;

  public ChatViewModel(Model model)
  {
    this.model = model;
    model.addListener(this);
    error = new SimpleStringProperty();
    messages = FXCollections.observableArrayList();
    textInput = new SimpleStringProperty();
  }

  // TODO: 2022. 03. 25.

  public void reset()
  {
    //put in everything from model.getAllmessages() from server
    error.set(null);
    textInput.set(null);
  }

  public void sendMessage()
  {
    if (textInput.get() == null)
    {
      error.set("Can't send an empty message!");
    }
    else
    {
      Message input = new Message(textInput.get(), model.getUsername());
      model.sendMessage(input);
      reset();
    }
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

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("NEW_MESSAGE")||evt.getPropertyName().equals("SEND_MESSAGE"))
    {
      Platform.runLater(() -> {
        Message temp = (Message) evt.getOldValue();

        messages.add(temp.toString());
      });
    }
  }

}

