package mediator;

import java.io.IOException;

public interface RemoteModel
{
  public void connect() throws IOException;
  public void disconnect() throws IOException;
  // public String convert(String source) throws IOException;
}
