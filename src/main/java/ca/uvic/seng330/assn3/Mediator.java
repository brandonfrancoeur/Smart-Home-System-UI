package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Device;
import ca.uvic.seng330.assn3.Client;

public interface Mediator {

  void unregister(Device device) throws HubRegistrationException;

  void unregister(Client client) throws HubRegistrationException;

  // not in spec, do not test
  void register(Device pDevice) throws HubRegistrationException;

  void register(Client pClient) throws HubRegistrationException;

  void alert(Device pDevice, String pMessage) throws HubRegistrationException;

  Status getStatus(Hub h1);

  void startup(Hub h1);

  void shutdown(Hub h1);
}
