package ca.uvic.seng330.assn3;

//
public interface deviceController {

  void toggle() throws HubRegistrationException;

  boolean getStatus();
}
