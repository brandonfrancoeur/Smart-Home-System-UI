package ca.uvic.seng330.assn3;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class login_page {

  private GridPane view;
  private TextField username;
  private TextField password;

  private loginController controller;

  public login_page(loginController controller) {

    this.controller = controller;

    view = layoutSetup.createAndConfigurePane(view);
    createAndLayoutControls();
  }

  public Parent login_page() {
    return view;
  }

  private void createAndLayoutControls() {

    Text t1 = new Text();
    t1.setText("Login page");

    username = new TextField();
    username.setMaxWidth(75);

    password = new TextField();
    password.setId("Password");
    password.setMaxWidth(75);

    Button login = new Button();
    login.setText("login");
    login.setMaxWidth(100);

    Button createUser = new Button();
    createUser.setText("Create User");
    createUser.setMaxWidth(100);

    view.addRow(0, t1);
    view.addRow(1, new Label("Username:"), username);
    view.addRow(2, new Label("password:"), password);
    view.addRow(3, createUser);
    view.addRow(4, login);

    login.setOnAction(
        e -> {
          try {
            controller.updateUser(username.getText());
            controller.updatePass(password.getText());
            controller.checkUser(controller.getUser(), controller.getPass());
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // call controller to make device page
    createUser.setOnAction(
        e -> {
          try {
            displyClientOptions();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        });
  }

  /*public void createAndConfigurePane() {
    view = new GridPane();

    ColumnConstraints leftCol = new ColumnConstraints();
    leftCol.setHalignment(HPos.RIGHT);
    leftCol.setHgrow(Priority.NEVER);

    ColumnConstraints rightCol = new ColumnConstraints();
    rightCol.setHgrow(Priority.SOMETIMES);

    view.getColumnConstraints().addAll(leftCol, rightCol);

    view.setAlignment(Pos.CENTER);
    view.setHgap(5);
    view.setVgap(10);
  }*/

  public void displyClientOptions() {

    createUser_page view = new createUser_page(controller);

    Scene scene = new Scene(view.createUser_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }
}
