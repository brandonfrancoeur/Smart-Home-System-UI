package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.HubRegistrationException;
import ca.uvic.seng330.assn3.Mediator;
import ca.uvic.seng330.assn3.Status;
import ca.uvic.seng330.assn3.devices.Temperature.Unit;
import ca.uvic.seng330.assn3.loginModel;

public class Thermostat extends Device {
  private Mediator aMed;
  private Status status = Status.OFF;
  private Temperature setPoint;

  public Thermostat(Mediator mediator) throws Temperature.TemperatureOutofBoundsException {
    super();
    setPoint = new Temperature(72, Unit.FAHRENHEIT);
    this.aMed = mediator;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
  }

  public Temperature.Unit getUnit() {
    return setPoint.getUnit();
  }

  public double getTemp() {
    double temp = setPoint.getTemperature();
    return temp;
  }

  @Override
  public Status getStatus() {
    return status;
  }

  public void setTemp(Temperature t) throws HubRegistrationException {
    setPoint = t;
    aMed.alert(this, "Setting temp to " + t.getTemperature());
    loginModel.addAlertList("Thermostat" + getIdentifier() + " changed temperature ");
    status = Status.NORMAL;
  }

  public void setStatus(Status s1) {
    status = s1;
  }

  @Override
  public String toString() {
    return "Thermostat id " + super.getIdentifier().toString();
  }

}
