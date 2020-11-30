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

// display device config screen
public class deviceConfig_page {

  private GridPane view2;

  private Text t1;

  private Button back;

  private deviceConfigController controller;

  public deviceConfig_page(deviceConfigController controller) {

    this.controller = controller;

    view2 = layoutSetup.createAndConfigurePane(view2);

    createAndLayoutControls();
  }

  public Parent deviceConfig_page() {
    return view2;
  }

  private void createAndLayoutControls() {

    t1 = new Text();
    t1.setText("Device configuration");

    back = new Button();
    back.setMaxWidth(40);
    back.setId("back");

    controller.createRow(view2);

    view2.addRow(0, t1);
    view2.addRow(4, new Label("back", back));

    // will toggle the device the button is assigned to

    back.setOnAction(
        e -> {
          try {
            controller.displayAdminPage();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // returns to login scene
  }
}
