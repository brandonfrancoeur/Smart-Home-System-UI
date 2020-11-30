package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Device;
import java.util.Map;
import javafx.scene.Scene;

import java.util.HashMap;
import java.util.UUID;

// controller for login checking and creating device pages
public class loginController {

  private final loginModel loginModel;

  public loginController(loginModel model) {
    this.loginModel = model;
  }

  public void updateUser(String user) {
    loginModel.setUser(user);
  }

  public void updatePass(String pass) {
    loginModel.setPassword(pass);
  }

  public String getPass() {
    return loginModel.getPassword();
  }

  public String getUser() {
    return loginModel.getUserID();
  }

  public void checkUser(String username, String password) {
    HashMap<UUID, Client> clients = loginModel.getClientList();

    for (Map.Entry<UUID, Client> entry : clients.entrySet()) {
      Client c = entry.getValue();
      if (c.getUser().equals(username) && c.getPassword().equals(password) && c.getAdmin()) {
        displayUserPage();
      } else if (c.getUser().equals(username)
          && c.getPassword().equals(password)
          && !c.getAdmin()) {
        displayAdminPage();
      }
    }
  }

  // function to add the Client to the list in model

  public void addClient(String user, String pass, boolean regUser) throws HubRegistrationException {

    // create a new client to add with name userMessage
    Client client = new Client(user, pass, regUser);
    ca.uvic.seng330.assn3.loginModel.getMediator().register(client);
  }

  public void displayAdminPage() {

    adminController ac1 = new adminController(Main.getloginModel());
    admin_page view2 = new admin_page(ac1);

    Scene scene = new Scene(view2.admin_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }

  public void displayUserPage() {

    deviceConfigController controller = new deviceConfigController(Main.getloginModel());
    user_page view = new user_page(controller);

    Scene scene = new Scene(view.user_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }
}
