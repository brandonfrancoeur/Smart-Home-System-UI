package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Device;
import ca.uvic.seng330.assn3.devices.Temperature;
import ca.uvic.seng330.assn3.devices.Temperature.Unit;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class thermoAction_page {

  private GridPane view2;
  private Text t1;
  private Button back;
  private thermoController tController;

  thermoAction_page(thermoController thermoC) {

    tController = thermoC;

    view2 = layoutSetup.createAndConfigurePane(view2);

    createAndLayoutControls();
  }

  public Parent thermoAction_page() {
    return view2;
  }

  private void createAndLayoutControls() {

    t1 = new Text();
    t1.setText("Device actions");

    // will turn record on and off
    Button start = new Button();
    start.setText("Start");
    start.setMaxWidth(150);
    start.setId("start");

    Button turnOff = new Button();
    turnOff.setText("Turn Off");
    turnOff.setMaxWidth(150);
    turnOff.setId("turnOff");

    // will convert the current temp to celsius
    Button celsius = new Button();
    celsius.setText("Celsius");
    celsius.setMaxWidth(150);
    celsius.setId("celsius");

    // will convert the current temp to fahrenheit
    Button fahrenheit = new Button();
    fahrenheit.setText("Fahrenheit");
    fahrenheit.setMaxWidth(150);
    fahrenheit.setId("fahrenheit");

    Text currentTemp = new Text();
    currentTemp.setId("currentTemp");

    TextField changeTemp = new TextField();
    changeTemp.setId("changeTemp");

    Text dStatus = new Text();
    dStatus.setId("dStatus");
    Text currentUnit = new Text();
    currentUnit.setId("currentUnit");

    if (tController.getStatus() == Status.ON) {
      double t1 = tController.getTemp();
      dStatus.setText("On");
      String str = Double.toString(t1);
      currentTemp.setText(str);
      if (tController.getUnit() == Unit.CELSIUS) {
        currentUnit.setText("Celsius");
      } else if (tController.getUnit() == Unit.FAHRENHEIT) {
        currentUnit.setText("Fahrenheit");
      }
    } else if (tController.getStatus() == Status.OFF) {
      dStatus.setText("Off");
    }

    back = new Button();
    back.setId("back");
    back.setText("back");
    back.setMaxWidth(150);

    view2.addRow(1, start);
    view2.addRow(2, turnOff);
    view2.addRow(3, new Label("Status:"), dStatus);
    view2.addRow(4, new Label("Input new temp"), changeTemp);
    view2.addRow(5, new Label("Current temp:"), currentTemp);
    view2.addRow(6, new Label("Current unit:"), currentUnit);
    view2.addRow(7, celsius);
    view2.addRow(8, fahrenheit);

    view2.addRow(9, back);

    back.setOnAction(
        e -> {
          try {
            displyDeviceConfigPage();

          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }); // returns to device config page
    start.setOnAction(
        e -> {
          try {

            if (tController.getStatus() == Status.OFF) {
              tController.setStatus(Status.ON);
              dStatus.setText("On");

              double temp = tController.getTemp();
              currentTemp.setText(Double.toString(temp));

              if (tController.getUnit() == Unit.CELSIUS) {
                currentUnit.setText("Celsius");
              } else if (tController.getUnit() == Unit.FAHRENHEIT) {
                currentUnit.setText("Fahrenheit");
              }
            }

          } catch (Exception e1) {
            e1.printStackTrace();
          }
        });
    turnOff.setOnAction(
        e -> {
          try {

            if (tController.getStatus() == Status.ON) {
              tController.setStatus(Status.OFF);
              dStatus.setText("Off");
              currentUnit.setText(" ");
              currentTemp.setText(" ");
            }
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        });
    changeTemp.setOnAction(
        e -> {
          try {
            if (tController.getStatus() == Status.ON) {
              String temp = changeTemp.getText();
              Double t1 = Double.parseDouble(temp);
              Temperature.Unit Unit = Temperature.Unit.CELSIUS;
              Temperature temperature = new Temperature(t1, Unit);
              tController.setTemp(temperature);
              currentTemp.setText(t1.toString());
              currentUnit.setText("Celsius");
            }
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        });
    celsius.setOnAction(
        e -> {
          try {
            if (tController.getUnit() == Unit.FAHRENHEIT) {
              double temp = tController.getTemp();
              double realTemp;
              realTemp = ((temp - 32.0) * (5.0 / 9.0));

              currentTemp.setText(Double.toString(realTemp));
              currentUnit.setText("Celsius");
              Temperature t1 = new Temperature(realTemp, Unit.CELSIUS);
              tController.setTemp(t1);
            }
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        });
    fahrenheit.setOnAction(
        e -> {
          try {
            if (tController.getUnit() == Unit.CELSIUS) {

              double temp = tController.getTemp();
              double realTemp;
              realTemp = (32 + (temp * 9.0 / 5.0));

              currentTemp.setText(Double.toString(realTemp));
              currentUnit.setText("Fahrenheit");
              Temperature t1 = new Temperature(realTemp, Unit.FAHRENHEIT);

              tController.setTemp(t1);
            }
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        });
  }

  public Text getStatus(Text t1, Device d1) {

    if (d1.getStatus() == Status.ON) {
      t1.setText("On");
    } else if (d1.getStatus() == Status.OFF) {
      t1.setText("Off");
    } else if (d1.getStatus() == Status.NORMAL) {
      t1.setText("Normal");
    } else if (d1.getStatus() == Status.ERROR) {
      t1.setText("Error");
    } else if (d1.getStatus() == Status.NOT_AVAILABLE) {
      t1.setText("Device is not available");
    }
    return t1;
  }

  public void displyDeviceConfigPage() {

    // adminModel model = new adminModel();
    deviceConfigController controller = new deviceConfigController(Main.getloginModel());
    deviceConfig_page view = new deviceConfig_page(controller);

    Scene scene = new Scene(view.deviceConfig_page(), 500, 600);
    Main.primaryStage.setScene(scene);
    Main.primaryStage.show();
  }
}
