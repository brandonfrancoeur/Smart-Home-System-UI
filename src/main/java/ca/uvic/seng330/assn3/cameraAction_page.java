package ca.uvic.seng330.assn3;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import java.io.File;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;

public class cameraAction_page {

  private GridPane view2;
    private Button manage_devices;
  private Button configDevices;

    List<Client> myList;

  private cameraController controller;
  private UUID ID1;

  public cameraAction_page(cameraController controller) {

    this.controller = controller;

    view2 = layoutSetup.createAndConfigurePane(view2);

    createAndLayoutControls();

  }

  public Parent cameraAction_page() {
    return view2;
  }

  private void createAndLayoutControls() {

      Text t1 = new Text();
    t1.setText("Device actions");

    // will turn record on and off
    Button record = new Button();
    record.setText("record");
    record.setMaxWidth(100);
    record.setId("record");

    Button cameraView = new Button();
    cameraView.setText("view");
    cameraView.setMaxWidth(100);
    cameraView.setId("cameraView");

    Button movementDetected = new Button();
    movementDetected.setMaxWidth(200);
    movementDetected.setId("movementDetected");

    Button leftRoom = new Button();
    leftRoom.setMaxWidth(200);
    leftRoom.setId("leftRoom");

    Button stopRecording = new Button();
    stopRecording.setText("Stop Recording");
    stopRecording.setMaxWidth(200);
    stopRecording.setId("stopRecording");

    Text currentUnit = new Text();
    currentUnit.setId("currentUnit");

    Button Start = new Button();
    Start.setText("Start");
    Start.setId("Start");

    Button turnOff = new Button();
    turnOff.setText("Turn Off");
    turnOff.setId("TurnOff");

    // HashMap<UUID, Device> map = adminModel.getDeviceList();

    Text recording = new Text();
    recording.setText(" ");

    Text dStatus = new Text();
    dStatus.setId("dStatus");
    // Device match = model.getCamera();

    if (controller.getStatus() == Status.ON) {
      dStatus.setText("On");
      if (controller.getRecording()) {
        recording.setText("Recording");
      } else if (!controller.getRecording()) {
        recording.setText("Not Recording");
      }
    } else if (controller.getStatus() == Status.OFF) {
      dStatus.setText("Off");
    }

      Button back = new Button();
    back.setMaxWidth(40);
    back.setId("back");


    view2.addRow(1, Start);
    view2.addRow(2, turnOff);
    view2.addRow(3, new Label("Detect Movement:"), movementDetected);
    view2.addRow(4, new Label("Empty Room:"), leftRoom);
    view2.addRow(5, record);
    view2.addRow(5, cameraView);
    view2.addRow(6, stopRecording);
    view2.addRow(7, new Label("Recording:"), recording);
    view2.addRow(8, new Label("Status:"), dStatus);
    // view2.addRow(3, new Label("Input new temp"), changeTemp);
    view2.addRow(9, new Label("back"), back);


      cameraView.setOnAction(
              e -> {
                  controller.displyVideo();
              });

    Start.setOnAction(
        e -> {
          controller.setStatus(Status.ON);
          dStatus.setText("On");
          recording.setText("Not Recording");
        });
    movementDetected.setOnAction(
        e -> {
          if (controller.getRecording()) {
            movementDetected.setText("Object Detected");
            controller.detectMovement();
          }
        });
    leftRoom.setOnAction(
        e -> {
          if (controller.getRecording()) {
            leftRoom.setText("Room is empty");
            controller.leftRoom();
          }
        });

    turnOff.setOnAction(
        e -> {
          controller.setStatus(Status.OFF);
          dStatus.setText("Off");
          recording.setText(" ");
        });

    back.setOnAction(
        e -> {
          try {
            controller.displyDeviceConfigPage();
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // returns to login scen
    record.setOnAction(
        e -> {
          try {
            controller.record();

            // if(!controller.getRecording())
            recording.setText("Recording");

          } catch (Exception e1) {
            e1.printStackTrace();
          }
        });
    stopRecording.setOnAction(
        e -> {
          try {
            controller.stopRecord();
            // if(controller.getRecording())
            recording.setText("Not Recording");

          } catch (Exception e1) {
            e1.printStackTrace();
          }
        });
  }
}
