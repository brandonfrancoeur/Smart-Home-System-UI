package ca.uvic.seng330.assn3.devices;

import ca.uvic.seng330.assn3.Status;

import java.util.UUID;

public abstract class Device {

  private final UUID id = UUID.randomUUID();
  private Status status; // This can't be NULL!

  public UUID getIdentifier() {
    return id;
  }


  public Status getStatus() {
    // Since the status can't be NULL, then check IF NULL and IF return dummy
    // status.
    return status == null ? Status.NOT_AVAILABLE : status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
