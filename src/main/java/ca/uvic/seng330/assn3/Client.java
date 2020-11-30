package ca.uvic.seng330.assn3;

import java.util.UUID;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// be able to get notifications
// two types adnim and user
// privileges for who can do what
// add device/remove
// list of device status get form Device
// shutdown turn all off and send message to client

// admin
// can: mange devices and users
// when logging in they have option to be user or adnim
// log if perv only when system is on before you log in

// user
// can:
public class Client {

  // set if user or adnim
  // use two const
  // boolean true = admin false = user
  public boolean admin;
  private final UUID uuid = UUID.randomUUID();
  private String userName;
  private String password;
  private Logger logger = LoggerFactory.getLogger(Hub.class);

  public Client(String user, String pass, boolean ad) {
    userName = user;
    password = pass;
    admin = ad;
  }

  public UUID getIdentifier() {
    return uuid;
  }

  public void notify(JSONObject json) {
    logger.info(json.toString());
  }

  public String getUser() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public boolean getAdmin() {
    return admin;
  }
}
