package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

// uses login model and controller
// display device config screen and launch specific device configs
public class deviceConfigController {

  private loginModel Model;

  public deviceConfigController(loginModel lModel) {

    this.Model = lModel;
  }

  // get the devices on current system
  HashMap<UUID, Device> getDeviceList() {
    return loginModel.getDeviceList();
  }

  private Text getName(Text t1, Device d1) {
    if (d1 instanceof Camera) {
      t1.setText("Camera");
    } else if (d1 instanceof Lightbulb) {
      t1.setText("Light Bulb");
    } else if (d1 instanceof Thermostat) {
      t1.setText("Thermostat");
    } else if (d1 instanceof SmartPlug) {
      t1.setText("SmartPlug");
    }

    return t1;
  }


  // will create a text box with the current status of a device
  public Text getStatus(Text t1, Device d1) {
    if (d1.getStatus() == Status.ON) {
      t1.setText("On");
    } else if (d1.getStatus() == Status.OFF) {
      t1.setText("Off");
    } else if (d1.getStatus() == Status.NORMAL) {
      t1.setText("Normal");
    } else if (d1.getStatus() == Status.ERROR) {
      t1.setText("Error");
    } else if (d1.getStatus() == Status.NOT_AVAILABLE) {
      t1.setText("Device is not available");
    }
    return t1;
  }

  // toggle device on and off
  // only smartplug and lightbulb
  private void toggleDevice(Device d1, Text t1) throws HubRegistrationException {

    // UUID
    if (d1 instanceof Lightbulb) {
      ((Lightbulb) d1).toggle();
      boolean test = ((Lightbulb) d1).getCondition();
      if (!test) {
        d1.setStatus(Status.OFF);
        t1.setText("Off");
      }
      if (test) {
        d1.setStatus(Status.ON);
        t1.setText("On");
      }
    }
    if (d1 instanceof SmartPlug) {
      ((SmartPlug) d1).toggle();
      boolean test = ((SmartPlug) d1).getState();
      if (!test) {
        d1.setStatus(Status.OFF);
        t1.setText("Off");
      }
      if (test) {
        d1.setStatus(Status.ON);
        t1.setText("On");
      }
    }
  }

  void addDevice(Device d1) {
    // to update device list in the loginModel
    Model.updateDeviceList(d1);
  }

  void createRow(GridPane view2) {

    // get the list of devices
    HashMap<UUID, Device> tmpList = getDeviceList();
    Set set = tmpList.entrySet();
    Device[] DeviceList = new Device[tmpList.size()];
    Iterator iterator = set.iterator();
    int i = 0;
    while (iterator.hasNext()) {
      Map.Entry mentry = (Map.Entry) iterator.next();
      DeviceList[i] = (Device) mentry.getValue();
      i++;
    }
    // create buttons for each device
    for (int rowIndex = 0; rowIndex < tmpList.size(); rowIndex++) {

      Text deviceId = new Text();
      deviceId.setText(String.valueOf(DeviceList[rowIndex].getIdentifier()));

      Button displayFunction = new Button();
      displayFunction.setText("functions");
      displayFunction.setMaxWidth(100);
      displayFunction.setId("functions");

      Button deviceToggle = new Button();
      deviceToggle.setText("toggle");
      deviceToggle.setMaxWidth(150);

      Text deviceStatus = new Text();
      Text t1 = new Text();
      deviceStatus = getStatus(deviceStatus, DeviceList[rowIndex]);
      Text deviceName = getName(t1, DeviceList[rowIndex]);

      view2.addRow(rowIndex + 1, deviceName);
      view2.addRow(rowIndex + 1, deviceId);

      if (DeviceList[rowIndex] instanceof Camera || DeviceList[rowIndex] instanceof Thermostat) {
        view2.addRow(rowIndex + 1, displayFunction);
      }

      if (DeviceList[rowIndex] instanceof Lightbulb || DeviceList[rowIndex] instanceof SmartPlug) {
        view2.addRow(rowIndex + 1, deviceToggle);
      }

      view2.addRow(rowIndex + 1, deviceStatus);

      int finalRowIndex = rowIndex;
      Text finalDeviceStatus1 = deviceStatus;

      deviceToggle.setOnAction(
          e -> {
            try {
              toggleDevice(DeviceList[finalRowIndex], finalDeviceStatus1);
            } catch (Exception e1) {
              e1.printStackTrace();
            }
          }); // returns to login scene

      int finalRowIndex1 = rowIndex;

      displayFunction.setOnAction(
          e -> {
            try {
              if (DeviceList[finalRowIndex1] instanceof Camera) {
                displyCameraActionPage(DeviceList[finalRowIndex].getIdentifier());
              }
              if (DeviceList[finalRowIndex1] instanceof Thermostat) {
                displaythermoActionPage(DeviceList[finalRowIndex].getIdentifier());
              }
            } catch (Exception e1) {
              e1.printStackTrace();
            }
          }); // returns to login scene
    }
  }

  void displayAdminPage() {

    admin_page view2 = new admin_page(new adminController(Main.getloginModel()));

    Scene scene = new Scene(view2.admin_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }

  void displayUserPage() {
    deviceConfigController controller = new deviceConfigController(Model);
    user_page view = new user_page(controller);

    Scene scene = new Scene(view.user_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }

  private void displaythermoActionPage(UUID t1) {

    HashMap<UUID, Device> map = getDeviceList();

    thermoController controller = new thermoController(map.get(t1));
    thermoAction_page view = new thermoAction_page(controller);

    Scene scene = new Scene(view.thermoAction_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }

  private void displyCameraActionPage(UUID Id) {
    HashMap<UUID, Device> map = getDeviceList();
    Device c = null;
    for (Map.Entry<UUID, Device> entry : map.entrySet()) {
      if (entry.getKey() == Id) {
        c = entry.getValue();
      }
    }


    cameraController controller = new cameraController(c);
    cameraAction_page view = new cameraAction_page(controller);

    Scene scene = new Scene(view.cameraAction_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }
}
