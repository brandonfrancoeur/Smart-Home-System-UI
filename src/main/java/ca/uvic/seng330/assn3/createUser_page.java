package ca.uvic.seng330.assn3;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicBoolean;

public class createUser_page {
  private loginController controller;
  private GridPane view2;
  private Button createUser;

  public createUser_page(loginController cController) {
    controller = cController;

    view2 = layoutSetup.createAndConfigurePane(view2);

    createAndLayoutControls();

  }

  public void createAndLayoutControls() {
    Button admin = new Button();
    admin.setText("Admin");
    admin.setMaxWidth(100);
    Boolean regUser;
    Button regularUser = new Button();
    regularUser.setText("Regular User");

    Text userType = new Text();
    userType.setText("Unselected");
    Text t1 = new Text();
    t1.setText("Create User");

    TextField username = new TextField();
    username.setMaxWidth(75);

    TextField password = new TextField();
    password.setId("Password");
    password.setMaxWidth(75);

    createUser = new Button();
    // createUser.setText("Create User");
    createUser.setMaxWidth(150);

    Button back = new Button();
    back.setText("Back");
    back.setMaxWidth(100);

    view2.addRow(0, t1);
    view2.addRow(1, new Label("Username:"), username);
    view2.addRow(2, new Label("password:"), password);
    view2.addRow(3, admin);
    view2.addRow(4, regularUser);
    view2.addRow(5, new Label("User Type:"), userType);
    view2.addRow(6, new Label("Create User"), createUser);
    view2.addRow(7, back);

    admin.setOnAction(
        e -> {
          userType.setText("Admin");
          createUser.setText(" ");
        });
    regularUser.setOnAction(
        e -> {
          userType.setText("Regular User");
          createUser.setText(" ");
        });

    createUser.setOnAction(
        e -> {
          try {
            if (userType.getText().equals("Regular User")) {
              controller.addClient(username.getText(), password.getText(), true);
              createUser.setText("User Created");
            } else if (userType.getText().equals("Admin")) {
              controller.addClient(username.getText(), password.getText(), false);
              createUser.setText("User Created");
            }

          } catch (HubRegistrationException e1) {
            e1.printStackTrace();
          }
        });

    back.setOnAction(
        e -> {
          displayLogin_page();
        });
  }

  public void displayLogin_page() {
    login_page view = new login_page(controller);

    Scene scene = new Scene(view.login_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }

  public Parent createUser_page() {
    return view2;
  }
}
