package ca.uvic.seng330.assn3;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

// device config for regular user
public class deviceConfigUser_page {

  private GridPane view2;
  private Text t1;
  private Button manage_devices;
  private Button configDevices;
  private Button back;

  private deviceConfigController controller;

  /**
   * @p aram controller
   */
  deviceConfigUser_page(deviceConfigController controller) {

    this.controller = controller;

    view2 = layoutSetup.createAndConfigurePane(view2);

    createAndLayoutControls();
  }

  Parent deviceConfigUser_page() {
    return view2;
  }

  private void createAndLayoutControls() {

    t1 = new Text();
    t1.setText("Device config page from user");

    t1 = new Text();
    t1.setText("Device configuration");

    manage_devices = new Button();
    manage_devices.setMaxWidth(40);

    configDevices = new Button();
    configDevices.setMaxWidth(40);

    back = new Button();
    back.setMaxWidth(40);

    controller.createRow(view2);

    view2.addRow(0, t1);
    view2.addRow(4, new Label("back", back));

    // will toggle the device the button is assigned to
    // test2.setOnAction(e -> toggleAction());

    back.setOnAction(
        e -> {
          try {
            controller.displayUserPage();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // returns to login scene
  }
}
