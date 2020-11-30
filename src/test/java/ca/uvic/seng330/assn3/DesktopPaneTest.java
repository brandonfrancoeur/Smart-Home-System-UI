package ca.uvic.seng330.assn3;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import java.awt.Label;
import java.io.IOException;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class DesktopPaneTest extends ApplicationTest {

  private Scene scene;
  private static loginModel model;

  static {
    try {
      model = new loginModel();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void start(Stage primaryStage) {

    loginController controller = new loginController(model);
    login_page view = new login_page(controller);

    scene = new Scene(view.login_page(), 400, 400);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static loginModel getModel(){
    return model;
  }

  @Test public void should_contain_login_button() {
    // expect:
    clickOn("login");
  }

  @Test public void test_login() {

    //given:
    write("admin");

    clickOn("#Password").write("password");
    // expect:
    clickOn("login");
  }


}