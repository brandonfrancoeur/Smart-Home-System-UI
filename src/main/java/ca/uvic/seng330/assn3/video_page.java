package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Device;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class video_page {

  private GridPane view;
  private ListView<Device> listDevice;
  private Button configDevices;
  private Button logout;

  @FXML private MediaView medView;
  private MediaPlayer medPlayer;
  private Media media;

  List<Client> myList;

  public video_page() {

    createAndConfigurePane();
    createAndLayoutControls();
  }

  public Parent video_page() {
    return view;
  }

  private void createAndLayoutControls() {

    Text t1 = new Text();
    t1.setText("Camera view");


      String p = new File("/Users/jonathankelly/IdeaProjects/assn3-team-brandon-and-jonathan/src/main/java/ca/uvic/seng330/assn3/vid.mp4").getAbsolutePath();
      media = new Media(new File(p).toURI().toString());
      medPlayer = new MediaPlayer(media);
      medView = new MediaView();
      medView.setMediaPlayer(medPlayer);
      medPlayer.setAutoPlay(true);


    view.addRow(1, t1);
    view.addRow(2, medView);

  }

  private void createAndConfigurePane() {
    view = new GridPane();

    ColumnConstraints leftCol = new ColumnConstraints();
    leftCol.setHalignment(HPos.RIGHT);
    leftCol.setHgrow(Priority.NEVER);

    ColumnConstraints rightCol = new ColumnConstraints();
    rightCol.setHgrow(Priority.SOMETIMES);

    view.getColumnConstraints().addAll(leftCol, rightCol);

    view.setAlignment(Pos.CENTER);
    view.setHgap(5);
    view.setVgap(10);
  }

}
