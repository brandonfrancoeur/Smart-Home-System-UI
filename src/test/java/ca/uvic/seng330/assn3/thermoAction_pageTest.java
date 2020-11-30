package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Temperature;
import ca.uvic.seng330.assn3.devices.Thermostat;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TextMatchers;

import java.io.IOException;
import java.util.UUID;

import static org.testfx.api.FxAssert.verifyThat;


public class thermoAction_pageTest extends ApplicationTest {

    private Scene scene;
    private Mediator h1;

    {
        try {
            h1 = new Hub();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private thermoController tController;

    private Client c1 = new Client("test", "password" , true);


    public thermoAction_pageTest() throws Temperature.TemperatureOutofBoundsException, HubRegistrationException {
        //this.t1 = new Thermostat(h1);
        h1.register(c1);
    }

    @Override
    public void start(Stage primaryStage) throws Temperature.TemperatureOutofBoundsException, HubRegistrationException {


        Thermostat t1 = new Thermostat(h1);
        thermoController controller = new thermoController(t1);
        thermoAction_page view  = new thermoAction_page(controller);

        scene = new Scene(view.thermoAction_page(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //test not working
    @Test
    public void thermo_startTest(){
        clickOn("#start");
        verifyThat("#dStatus", TextMatchers.hasText("On"));
    }

    @Test
    public void thermo_find_turnOff() {
        clickOn("#turnOff");
        verifyThat("#dStatus", TextMatchers.hasText("Off"));
    }

    @Test
    public void thermo_find_celsius() {
        clickOn("#celsius");
    }

    @Test
    public void thermo_find_fahrenheit() {
        clickOn("#fahrenheit");
    }

    @Test
    public void thermo_setTemp() {
        clickOn("#start");
        clickOn("#changeTemp").write("2");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        clickOn("#celsius");
        verifyThat("#currentTemp", TextMatchers.hasText("2.0"));
        verifyThat("#currentUnit", TextMatchers.hasText("Celsius"));

        //check convte to F
        clickOn("#fahrenheit");
        verifyThat("#currentTemp", TextMatchers.hasText("35.6"));
        verifyThat("#currentUnit", TextMatchers.hasText("Fahrenheit"));

    }

    @Test
    public void temp_OutOfBoundsTest() throws Temperature.TemperatureOutofBoundsException {

        clickOn("#start");
        clickOn("#changeTemp").write("1001");
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn("#celsius");



    }



}
