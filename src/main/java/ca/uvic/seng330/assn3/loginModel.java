package ca.uvic.seng330.assn3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.uvic.seng330.assn3.devices.Device;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// hold userId and password and device list and user list
// up loads the offline storage
public final class loginModel {

  public String userID;
  public String password;
  private Logger logger = LoggerFactory.getLogger(Hub.class);
  // private static final adminModel aModel = new adminModel();
  // have list of devices
  private static HashMap<UUID, Device> deviceList;
  private HashMap<UUID, Client> ClientList;
  private HashMap<UUID, deviceController> deviceControllerList;
  private static Mediator med;
  private static Hub h1;
  private static List<String> alertList = new ArrayList<>();

  static {
    try {
      med = new Hub();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public loginModel() throws IOException {
    h1 = new Hub();
    deviceList = new HashMap<>();
    this.ClientList = new HashMap<>();
    this.deviceControllerList = new HashMap<>();
  }

  public static void addAlertList(String s1) {
    alertList.add(s1);
  }

  public static List<String> getAlertList() {
    return alertList;
  }

  public static Hub getHub() {
    return h1;
  }

  public static Mediator getMediator() {
    return med;
  }

  // check that the user is an admin
  public boolean authenticate(String user, String pass) {
    userID = user;
    password = pass;
    for (Map.Entry<UUID, Client> entry : ClientList.entrySet()) {
      Client c = entry.getValue();
      if (c.getUser().equals(user) && c.getPassword().equals(pass)) {
        return true;
      }
    }
    return false;
  }

  public void setUser(String user) {
    userID = user;
  }

  public void setPassword(String pass) {
    password = pass;
  }

  public String getUserID() {
    return userID;
  }

  public String getPassword() {
    return password;
  }

  public static HashMap<UUID, Device> getDeviceList() {
    return deviceList;
  }


  public Device getDevice(UUID id) {
    return deviceList.get(id);
  }
  public Client getClient(UUID id) { return ClientList.get(id);}

  // have list of users
  public void updateDeviceList(Device d) {
    deviceList.put(d.getIdentifier(), d);
  }

  public HashMap<UUID, Client> getClientList() {
    return ClientList;
  }

  // offline storage
  // read file and input devices
  // format: device type, ID, Status, current temp/diskSize

}
