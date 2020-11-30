package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;
import ca.uvic.seng330.assn3.Status;
import ca.uvic.seng330.assn3.loginModel;

public class Lightbulb extends Device implements SwitchableDevice {

  private boolean isOn = false;//default to false
  private Mediator aMed;
  private Status s;

  public Lightbulb(Mediator pMed) {
    super();
    aMed = pMed;
    isOn = false;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
  }

  public void setStatus(Status s) {
    if (s == Status.ON) {
      loginModel.addAlertList("Lightbulb " + getIdentifier() + " was turned on");
    }
    if (s == Status.OFF) {
      loginModel.addAlertList("Lightbulb " + getIdentifier() + " was turned off");
    }
    this.s = s;
  }

  public boolean getCondition() {
    return isOn;
  }

  public Status getStatus() {
    return s;
  }

  @Override
  public String toString() {
    return "Lightbulb id " + super.getIdentifier().toString();
  }

  @Override
  public void toggle() throws HubRegistrationException {
    isOn = !isOn;
    String status = "lightbulb is now ";
    aMed.alert(this, status + isOn);
  }

}
