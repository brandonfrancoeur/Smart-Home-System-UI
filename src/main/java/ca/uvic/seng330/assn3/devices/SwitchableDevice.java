package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.HubRegistrationException;

// interface for devices that toggle
public interface SwitchableDevice {

  public void toggle() throws HubRegistrationException;
}
