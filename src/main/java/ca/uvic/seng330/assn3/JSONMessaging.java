package ca.uvic.seng330.assn3;

import java.util.Date;
import org.json.JSONObject;
import ca.uvic.seng330.assn3.devices.Device;

public class JSONMessaging {

  private JSONObject j1;
  private Device d1;
  private String m1;

  public JSONMessaging(Device inD1, String inM1) {
    d1 = inD1;
    m1 = inM1;
  }

  public JSONObject invoke() {

    j1 = new JSONObject();
    j1.put("msg_id", 1);
    j1.put("node_id", d1.getIdentifier());
    j1.put("status", d1.getStatus());
    j1.put("payload", m1);
    Date Date = new Date();
    j1.put("created_at", Date.toString());

    return j1;
  }

  /*{
      "msg_id": 1,
          "node_id": "the device id",
          "status": "device dependent status",
          "payload":  "some devices will send a string representing some data",
          "created_at": "2011-04-18T23:23:56Z"
  }*/

}
