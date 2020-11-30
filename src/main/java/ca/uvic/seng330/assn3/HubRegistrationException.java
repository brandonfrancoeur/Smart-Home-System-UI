package ca.uvic.seng330.assn3;

public class HubRegistrationException extends Exception {

  public HubRegistrationException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  public HubRegistrationException(String errorMessage) {
    super(errorMessage);
  }
}
