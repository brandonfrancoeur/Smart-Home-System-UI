package ca.uvic.seng330.assn3;

import java.io.IOException;

import java.util.HashMap;
import java.util.UUID;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uvic.seng330.assn3.devices.Device;

import java.lang.String;

// system on the hub handles reg and remove devices and log it
//

public class Hub extends Device implements Mediator {

  private Status status = Status.ON;
  static final Logger logger = LoggerFactory.getLogger(Hub.class.getName());


  HashMap<Client, Device> deviceMap = new HashMap<Client, Device>(); // list containing all the devices connected

  private HashMap<UUID, Device> aDevices;
  private HashMap<UUID, Client> aClients;

  private Status stat;
  private final UUID id = UUID.randomUUID();
  // create a log
  private loginModel lModel;

  public Hub() throws IOException {
    System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
    System.setProperty(org.slf4j.impl.SimpleLogger.LOG_FILE_KEY, "debug.log");
    lModel = Main.getloginModel();
  }

  // put device in list
  public void updateDeviceList(Device d1) {}

  public void notify(JSONObject pMsg) {}

  // get the current list for loginModel
  public HashMap<UUID, Device> getDevicesList() {
    return Main.getloginModel().getDeviceList();
  }

  public Status getStatus(Hub h1) {
    return h1.stat;
  }
  /**
   * Logging. Use SLF4J to write all message traffic to the log file.
   *
   * @param logMsg
   */
  public void log(String logMsg) {
    logger.info(logMsg);
  }

  // @Override
  public void register(Device pDevice) throws HubRegistrationException {
    aDevices = Main.getloginModel().getDeviceList();
    if (!aDevices.containsKey(pDevice.getIdentifier())) {
      aDevices.put(pDevice.getIdentifier(), pDevice);
      log("Device registered: " + pDevice);
      alert(pDevice, "device has been created ");
    } else {
      throw new HubRegistrationException(pDevice + " was already registered");
    }
  }

  // @Override
  public void register(Client pClient) throws HubRegistrationException {
    aClients = Main.getloginModel().getClientList();
    if (!aClients.containsKey(pClient.getIdentifier())) {
      aClients.put(pClient.getIdentifier(), pClient);
      loginModel.addAlertList("Client registered: " + pClient.getUser());
    } else {
      throw new HubRegistrationException(pClient.getUser() + " was already registered");
    }
  }

  // @Override
  public void unregister(Device device) throws HubRegistrationException {
    if (!aDevices.containsKey(device.getIdentifier())) {
      log("Unknown Device unregister");
      throw new HubRegistrationException("Device does not exists!");
    }
    aDevices.remove(device.getIdentifier());
    // log("Device removed " + device);
    loginModel.addAlertList("Device removed " + device);
  }

  // @Override
  public void unregister(Client client) throws HubRegistrationException {
    if (!aClients.containsKey(client.getIdentifier())) {
      throw new HubRegistrationException("Client does not exists!");
    }
    aClients.remove(client.getIdentifier());
    loginModel.addAlertList("Client removed " + client.getUser());
  }

  public UUID getIdentifier() {
    return id;
  }

  // set status to off
  public void startup(Hub h1) {
    h1.stat = Status.ON;
  }

  // set status to on
  public void shutdown(Hub h1) {
    h1.stat = Status.OFF;
  }

  /**
   * Alerts are events that happen at the Device level. They send the alert to the hUb, which
   * redistributes to the clients
   *
   * @param pMessage
   */
  public void alert(Device pDevice, String pMessage) throws HubRegistrationException {

    // initialize the map
    JSONObject jsonMessage = new JSONMessaging(pDevice, pMessage).invoke();

    // ordinarily, we would have logic for which clients to notify
    notifyClients(jsonMessage);
    log("ALERT msg: " + pMessage + " - from Device " + pDevice.toString());
  }

  private void notifyClients(JSONObject pMsg) {

    for (Client c : aClients.values()) {
      c.notify(pMsg);
      log("Notified: " + c.toString());
    }
  }

  public HashMap<UUID, Device> getDevices() {
    return new HashMap<UUID, Device>(aDevices);
  }

  public HashMap<UUID, Client> getClients() {
    return new HashMap<UUID, Client>(aClients);
  }
}
