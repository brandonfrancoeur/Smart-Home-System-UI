package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Device;
import ca.uvic.seng330.assn3.devices.Temperature;
import ca.uvic.seng330.assn3.devices.Temperature.TemperatureOutofBoundsException;
import ca.uvic.seng330.assn3.devices.Thermostat;

public class thermoController {

  private Thermostat thermostat;

  public thermoController(Device d1) {
    thermostat = (Thermostat) d1;
  }

  public double getTemp() {
    double temp = thermostat.getTemp();
    return temp;
  }

  public void setTemp(Temperature t) throws HubRegistrationException {
    thermostat.setTemp(t);
  }

  public Temperature.Unit getUnit() {
    return thermostat.getUnit();
  }

  public Status getStatus() {
    return thermostat.getStatus();
  }

  public void setStatus(Status s) {
    thermostat.setStatus(s);
  }
}
