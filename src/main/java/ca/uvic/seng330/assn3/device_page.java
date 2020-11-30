package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Camera;
import ca.uvic.seng330.assn3.devices.Device;
import ca.uvic.seng330.assn3.devices.Lightbulb;
import ca.uvic.seng330.assn3.devices.SmartPlug;
import ca.uvic.seng330.assn3.devices.Thermostat;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

// display the addDevice page
public class device_page {

    private GridPane view;

    private deviceConfigController controller;

  public device_page(deviceConfigController dC) {

    controller = dC;

    view = layoutSetup.createAndConfigurePane(view);

    createAndLayoutControls();
  }

  public Parent device_page() {
    return view;
  }

  private void createAndLayoutControls() {

    // adminModel model = new adminModel();
    // adminController controller = new adminController(model);
      Text t11 = new Text();
    t11.setText("click on device to add to current user");

      Button addCamera = new Button();
    addCamera.setMaxWidth(40);
    addCamera.setId("addCamera");

      Button addLightbulb = new Button();
    addLightbulb.setMaxWidth(40);
    addLightbulb.setId("addLightbulb");

      Button addSmartPlug = new Button();
    addSmartPlug.setMaxWidth(40);
    addSmartPlug.setId("addSmartPlug");

      Button addThermostat = new Button();
    addThermostat.setMaxWidth(40);
    addThermostat.setId("addThermostat");

      Button back = new Button();
    back.setMaxWidth(40);
    back.setId("back");

    view.addRow(0, t11);
    view.addRow(1, new Label("Camera", addCamera));
    view.addRow(1, new Label("Light", addLightbulb));
    view.addRow(2, new Label("Smart plug", addSmartPlug));
    view.addRow(2, new Label("Thermostat", addThermostat));
    view.addRow(3, new Label("back", back));

    back.setOnAction(
        e -> {
          try {
            controller.displayAdminPage();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // returns to admin scene

    addCamera.setOnAction(
        e -> {
          try {
            Device c1 = new Camera(loginModel.getMediator());
            loginModel.addAlertList("Camera" + c1.getIdentifier() + "was registered");
            controller.addDevice(c1);
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // create a new camera

    addLightbulb.setOnAction(
        e -> {
          try {
            Device l1 = new Lightbulb(loginModel.getMediator());
            loginModel.addAlertList("Lightbulb" + l1.getIdentifier() + "was registered");
            controller.addDevice(l1);
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // create a new lightbulb

    addSmartPlug.setOnAction(
        e -> {
          try {
            Device s1 = new SmartPlug(loginModel.getMediator());
            loginModel.addAlertList("SmartPlug" + s1.getIdentifier() + "was registered");
            controller.addDevice(s1);
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // create a new smartplug

    addThermostat.setOnAction(
        e -> {
          try {
            Device t1 = new Thermostat(loginModel.getMediator());
            loginModel.addAlertList("Thermostat" + t1.getIdentifier() + "was registered");
            controller.addDevice(t1);
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // create a new thermostat
  }
}
