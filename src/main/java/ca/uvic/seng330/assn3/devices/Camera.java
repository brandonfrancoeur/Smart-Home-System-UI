package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.loginModel;
import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;
import ca.uvic.seng330.assn3.Status;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Camera extends Device {

  private boolean isRecording;

  private UUID unique = UUID.randomUUID();

  private int diskSize;
  private Mediator aMed;
  private Status cstatus;

  public Camera(Mediator pmed) {
    super();
    aMed = pmed;
    diskSize = 0;
    isRecording = false;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      // in future, log this
    }
  }


  public void setStatus(Status s1) {
    assert s1 != null;
    cstatus = s1;
  }

  @Override
  public Status getStatus() {
    if (isRecording) {
      return Status.ON;
    } else {
      return Status.OFF;
    }
  }

  @Override
  public UUID getIdentifier() {
    return unique;
  }

  public boolean getisRecording() {
    return isRecording;
  }

  public int getDiskSize() {
    return diskSize;
  }

  //sets isRecording to true, incenses diskSize and alerts the user
  public void record() throws CameraFullException, HubRegistrationException {
    if (diskSize >= 100) {
      loginModel.addAlertList("Camera : " + getIdentifier() + " is full");
      // hub.log("Camera is Full");
      throw new CameraFullException("Camera is full");
    }
    isRecording = true;
    loginModel.addAlertList("Camera : " + getIdentifier() + " is turned on");
    aMed.alert(this,"Camera turned on");
    diskSize++;
    setStatus(Status.ON);
  }

  //sets isRecording to false and alerts the user
  public void stopRecord() {
    isRecording = false;
    loginModel.addAlertList("Camera : " + getIdentifier() + " is turned off");
    // hub.log("Camera turned off");
    setStatus(Status.OFF);
  }

  public boolean isFull() {
    if (diskSize >= 100) {
      return true;
    }
    return false;
  }

  public void detectMovement() {
    loginModel.addAlertList(("Camera : " + getIdentifier() + " detected movement"));
    HashMap<UUID, Device> map = loginModel.getDeviceList();
    for (Map.Entry<UUID, Device> entry : map.entrySet()) {
      Device d = entry.getValue();
      if (d instanceof Lightbulb) {
        d.setStatus(Status.ON);
      }
    }
  }

  public void leftRoom() {
    loginModel.addAlertList(("Camera : " + getIdentifier() + " detected no person in the room"));
    HashMap<UUID, Device> map = loginModel.getDeviceList();
    for (Map.Entry<UUID, Device> entry : map.entrySet()) {
      Device d = entry.getValue();
      if (d instanceof Lightbulb) {
        d.setStatus(Status.OFF);
      }
    }
  }
}
