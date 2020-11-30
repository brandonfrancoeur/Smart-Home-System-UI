package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;

public class SmartPlug extends Device implements SwitchableDevice {

  private Mediator aMed;
  private boolean isOn;

  public SmartPlug(Mediator med) {
    super();
    aMed = med;
    isOn = false;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
  }

  public boolean getState() {
    return isOn;
  }

  @Override
  public void toggle() throws HubRegistrationException {
    isOn = !isOn;
    String status = "plug is now ";
    aMed.alert(this, status + isOn);
  }

  @Override
  public String toString() {
    return "Smartplug id " + super.getIdentifier().toString();
  }
}
