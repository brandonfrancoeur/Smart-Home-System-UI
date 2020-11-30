package ca.uvic.seng330.assn3;

import java.util.*;

import ca.uvic.seng330.assn3.devices.*;

import javafx.scene.Scene;

// display adminPage and launch pages found on adminPage
public class adminController {

  private loginModel Model;
  // private adminController aController;
  // public Hub h = new Hub();

  adminController(loginModel model) {
    this.Model = model;
  }

  // gets the current data stored in ClientList
  public HashMap<UUID, Client> updateClientList() {
    return Model.getClientList();
  }

  // gets the current data stored in DeviceList
  public HashMap<UUID, Device> updateDeviceList() {
    return Model.getDeviceList();
  }

  public String getHubStatus() {

    if (loginModel.getMediator().getStatus(loginModel.getHub()) == Status.ON) {
      return "On";
    } else {
      return "Off";
    }
  }

  // method
  public void removeDevice(String s1) throws HubRegistrationException {

    s1 = remove(s1, " ");
    s1 = s1.trim();
    s1 = remove(s1, " ");
    s1 = s1.trim();
    UUID id = UUID.fromString(s1);
    loginModel.getMediator().unregister(Model.getDevice(id));
    displyAdminPage();
  }
  public void removeClient(UUID s1) throws HubRegistrationException {
    //UUID id = UUID.fromString(s1);
        loginModel.getMediator().unregister(Model.getClient(s1));

    displyAdminPage();
  }

  public static String remove(String str, String remove) {
    return str.substring(str.indexOf(remove));
  }

  public static String removeNext(String str, String remove) {
    return str.substring(str.indexOf(remove) + 1);
  }

  public String[] deviceNameList() {
    HashMap<UUID, Device> tmpList = updateDeviceList();
    Set set = tmpList.entrySet();
    int size = tmpList.size();
    Device[] DeviceList = new Device[tmpList.size()];
    Iterator iterator = set.iterator();
    int i = 0;
    while (iterator.hasNext()) {
      Map.Entry mentry = (Map.Entry) iterator.next();
      DeviceList[i] = (Device) mentry.getValue();
      i++;
    }
    String[] NameList;
    NameList = getName(DeviceList, size);

    return NameList;
  }
  public UUID[] clientNameList() {
    // TODO problem
    HashMap<UUID, Client> tmpList = updateClientList();
    Set set = tmpList.entrySet();
    Iterator iterator = set.iterator();
    int i = 0;
    UUID[] NameList = new UUID[tmpList.size()];
    while (iterator.hasNext()) {
      Map.Entry mentry = (Map.Entry) iterator.next();
      NameList[i] = ((Client) mentry.getValue()).getIdentifier();
      i++;
    }
   // String[] NameList;

    return NameList;
  }

  public String[] getName(Device[] DeviceList, int size) {
    String[] t1 = new String[size];
    for (int i = 0; i < size; i++) {

      if (DeviceList[i] instanceof Camera) {
        t1[i] = "Camera id: " + (DeviceList[i].getIdentifier());
      } else if (DeviceList[i] instanceof Lightbulb) {
        t1[i] = "LightBulb id: " + (DeviceList[i].getIdentifier());
      } else if (DeviceList[i] instanceof Thermostat) {
        t1[i] = "Thermostat id: " + (DeviceList[i].getIdentifier());
      } else if (DeviceList[i] instanceof SmartPlug) {
        t1[i] = "SmartPlug id: " + (DeviceList[i].getIdentifier());
      }
    }
    return t1;
  }

  // turns the hub on
  public void startUp() {
    loginModel.getMediator().startup(loginModel.getHub());
    displyAdminPage();
  }

  // turns the hub off and all devices off
  public void shutDown() {
    loginModel.getMediator().shutdown(loginModel.getHub());

    HashMap<UUID, Device> tmpList = updateDeviceList();
    Set set = tmpList.entrySet();
    Iterator iterator = set.iterator();
    while (iterator.hasNext()) {
      Map.Entry mentry = (Map.Entry) iterator.next();
      Device d1 = (Device) mentry.getValue();
      d1.setStatus(Status.OFF);
    }
    displyAdminPage();
  }

  public List<String> getLogList() {
    return loginModel.getAlertList();
  }

  // refresh the page
  public void displyAdminPage() {
    adminController ac1 = new adminController(Main.getloginModel());
    admin_page view2 = new admin_page(ac1);

    Scene scene = new Scene(view2.admin_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }

  // will open up the list of possible Clients to add
  public void displayLoginPage() {

    loginController lcontroller = new loginController(Main.getloginModel());
    login_page view = new login_page(lcontroller);

    Scene scene = new Scene(view.login_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }

  public void displyDevicePage() {

    deviceConfigController controller = new deviceConfigController(Model);
    device_page view2 = new device_page(controller);

    Scene scene = new Scene(view2.device_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }

  public void displyDeviceConfigPage() {

    deviceConfigController dController = new deviceConfigController(Model);
    deviceConfig_page view = new deviceConfig_page(dController);

    Scene scene = new Scene(view.deviceConfig_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }
}
