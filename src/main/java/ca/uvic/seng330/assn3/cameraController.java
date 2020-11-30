package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Camera;
import ca.uvic.seng330.assn3.devices.CameraFullException;
import ca.uvic.seng330.assn3.devices.Device;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;
import javafx.scene.Scene;

public class cameraController {
  private Camera cam;

  public cameraController(Device d1) {
    cam = (Camera) d1;
  }

  public void record() throws CameraFullException, HubRegistrationException {
    cam.record();
  }

  public void stopRecord() {
    cam.stopRecord();
  }

  public void detectMovement() {
    cam.detectMovement();
  }
 
  public void displyDeviceConfigPage() {

    deviceConfigController controller = new deviceConfigController(Main.getloginModel());
    deviceConfig_page view = new deviceConfig_page(controller);

    Scene scene = new Scene(view.deviceConfig_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }

  public void displyVideo(){

    video_page view = new video_page();

    Scene scene = new Scene(view.video_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();

  }

  public boolean getRecording() {
    return cam.getisRecording();
  }

  public Status getStatus() {
    return cam.getStatus();
  }

  public void setStatus(Status s1) {
    cam.setStatus(s1);
  }

  public void leftRoom() {
    cam.leftRoom();
  }
}
