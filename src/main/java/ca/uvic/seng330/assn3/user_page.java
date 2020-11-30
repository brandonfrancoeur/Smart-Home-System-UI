package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Device;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.ListView;

// display the regular user page
class user_page {

  private GridPane view;
  private deviceConfigController controller;

  user_page(deviceConfigController controller) {

    this.controller = controller;
    view = layoutSetup.createAndConfigurePane(view);

    createAndLayoutControls();
  }

  Parent user_page() {
    return view;
  }

  private void createAndLayoutControls() {

    ListView<Device> listDevice = new ListView<>();
    listDevice.getItems().addAll(controller.getDeviceList().values());
    listDevice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    listDevice.setMaxHeight(150);

    Button configDevices = new Button();
    configDevices.setText("device config");
    configDevices.setMaxWidth(150);

    Button logout = new Button();
    logout.setText("logout");
    logout.setMaxWidth(100);

    view.addRow(2, new Label("List of devices:"), listDevice);
    view.addRow(3, configDevices);
    view.addRow(4, logout);

    logout.setOnAction(
        e -> {
          try {
            displayLoginPage();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // returns to login scene

    configDevices.setOnAction(
        e -> {
          try {
            displyDeviceConfigPageUser();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // display device adding page
  }

  private void displyDeviceConfigPageUser() {

    deviceConfigUser_page view = new deviceConfigUser_page(controller);

    Scene scene = new Scene(view.deviceConfigUser_page(), 600, 400);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }

  private void displayLoginPage() {
    loginController controller = new loginController(Main.getloginModel());
    login_page view = new login_page(controller);

    Scene scene = new Scene(view.login_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }
}
