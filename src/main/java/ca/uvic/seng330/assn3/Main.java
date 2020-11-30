package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Temperature.TemperatureOutofBoundsException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.UUID;
import java.util.HashMap;

public class Main extends Application {

  static Stage primaryStage;
  Scene scene;
  private static loginModel lModel;

  static {
    try {
      lModel = new loginModel();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  public static loginModel getloginModel() {
    return lModel;
  }

  @Override
  public void start(Stage primaryStage) {

    this.primaryStage = primaryStage;

    loginController controller = new loginController(lModel);
    login_page view = new login_page(controller);

    scene = new Scene(view.login_page(), 500, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
