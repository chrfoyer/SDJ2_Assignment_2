package viewmodel;

import model.Model;

public class ViewModelFactory
{

  private LoginViewModel loginViewModel;
  private ChatViewModel chatViewModel;

  public ViewModelFactory(Model model)
  {
    chatViewModel = new ChatViewModel(model);
    loginViewModel = new LoginViewModel(model);

  }

  public ChatViewModel getChatViewModel()
  {
    return chatViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

}
