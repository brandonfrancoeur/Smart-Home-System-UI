package ca.uvic.seng330.assn3;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// display adminPage
public class admin_page {

  private GridPane view2;
  private ListView<String> listDevice = new ListView<>();
  private ListView<UUID> listUser = new ListView<>();
  private ListView<String> logList = new ListView<>();
  private adminController controller;

  public admin_page(adminController controller) {

    this.controller = controller;

    view2 = layoutSetup.createAndConfigurePane(view2);

    createAndLayoutControls();

  }

  public Parent admin_page() {
    return view2;
  }

  private void createAndLayoutControls() {

    Text t1 = new Text();
    t1.setText("Admin page");

    listUser.getItems().addAll(controller.clientNameList());
    listUser.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    listUser.setMaxHeight(100);
    listUser.setId("listUser");

    listDevice.getItems().addAll(controller.deviceNameList());
    listDevice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    listDevice.setMaxHeight(100);
    listDevice.setId("listDevice");

    logList.getItems().addAll(controller.getLogList());
    logList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    logList.setMaxHeight(100);
    logList.setId("logList");

    Button removeUser = new Button();
    removeUser.setText("remove user");
    removeUser.setMaxWidth(150);
    removeUser.setId("removeUser");

    Button manage_devices = new Button();
    manage_devices.setText("add device");
    manage_devices.setMaxWidth(150);
    manage_devices.setId("manage_devices");

    Button removeDevice = new Button();
    removeDevice.setText("remove device");
    removeDevice.setMaxWidth(150);
    removeDevice.setId("removeDevice");

    Button configDevices = new Button();
    configDevices.setText("device actions");
    configDevices.setMaxWidth(150);
    configDevices.setId("configDevices");

    Button logout = new Button();
    logout.setText("logout");
    logout.setMaxWidth(100);


    Button Startup = new Button();
    Startup.setText("Start system");
    Startup.setMaxWidth(100);
    logout.setId("Startup");

    Button Shutdown = new Button();
    Shutdown.setText("Shutdown");
    Shutdown.setMaxWidth(100);
    Shutdown.setId("Shutdown");

    Text hubStatus = new Text();
    hubStatus.setText(controller.getHubStatus());
    hubStatus.setId("hubStatus");

    view2.addRow(0, t1);
    view2.addRow(1, new Label("Hub status: "), hubStatus);
    view2.addRow(2, new Label("activity log"), logList);
    view2.addRow(3, new Label("Manage users:"), listUser);
    view2.addRow(4, removeUser);
    view2.addRow(5, new Label("List of devices:"), listDevice);
    view2.addRow(6, manage_devices);
    view2.addRow(6, removeDevice);
    view2.addRow(6, configDevices);
    view2.addRow(7, logout);
    view2.addRow(7, Startup);
    view2.addRow(7, Shutdown);

    logout.setOnAction(
        e -> {
          try {
            controller.displayLoginPage();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // returns to login scene

    manage_devices.setOnAction(
        e -> {
          try {
            if (loginModel.getMediator().getStatus(loginModel.getHub()) == Status.ON) {
              controller.displyDevicePage();
            }
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // display device adding page

    removeUser.setOnAction(
        e -> {
          try {
            HashMap<UUID, Client> cList = (HashMap<UUID, Client>) controller.updateClientList();

            if (loginModel.getMediator().getStatus(loginModel.getHub()) == Status.ON) {
              ObservableList<UUID> user;
              user = listUser.getSelectionModel().getSelectedItems();

              // update the view page
              for (UUID id : user){
                controller.removeClient(id);
                listUser.getItems().addAll(controller.clientNameList());
              }
            }
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // will remove the device form login model

    removeDevice.setOnAction(
        e -> {
          try {
            if (loginModel.getMediator().getStatus(loginModel.getHub()) == Status.ON) {
              ObservableList<String> devices;
              devices = listDevice.getSelectionModel().getSelectedItems();
              // update the view page
              for (String s1 : devices) {
                controller.removeDevice(s1);
                listDevice.getItems().addAll(controller.deviceNameList());
              }
            }
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // will remove the device form login model

    configDevices.setOnAction(
        e -> {
          try {
            if (loginModel.getMediator().getStatus(loginModel.getHub()) == Status.ON) {
              controller.displyDeviceConfigPage();
            }
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // display device adding page
    Shutdown.setOnAction(
        e -> {
          try {
            controller.shutDown();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // turns System off
    Startup.setOnAction(
        e -> {
          try {
            controller.startUp();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // turns System on
  }
}
