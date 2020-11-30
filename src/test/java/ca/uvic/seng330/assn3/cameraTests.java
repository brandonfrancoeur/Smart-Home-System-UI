package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Camera;
import ca.uvic.seng330.assn3.devices.Device;
import ca.uvic.seng330.assn3.devices.Temperature;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TextMatchers;
import ca.uvic.seng330.assn3.devices.CameraFullException;

import java.io.IOException;
import java.util.UUID;

import static org.testfx.api.FxAssert.verifyThat;

public class cameraTests extends ApplicationTest {

    private Scene scene;
    Mediator Mediator = new Hub();

    //loginModel model = DesktopPaneTest.getModel();
    private Client c1 = new Client("test", "password", true);


    public cameraTests() throws IOException, HubRegistrationException {
        Mediator.register(c1);
    }

    @Override
    public void start(Stage primaryStage) throws Temperature.TemperatureOutofBoundsException {

        Camera c1 = new Camera(Mediator);
        cameraController controller = new cameraController(c1);
        cameraAction_page view  = new cameraAction_page(controller);

        scene = new Scene(view.cameraAction_page(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void camera_recordTest() {
        clickOn("#Start");
        verifyThat("#dStatus", TextMatchers.hasText("On"));

        clickOn("#TurnOff");
        verifyThat("#dStatus", TextMatchers.hasText("Off"));
    }

    @Test(expected = CameraFullException.class)
    public void camera_fullTest() throws CameraFullException {

        for (int i = 0; i <= 101; i++){
            clickOn("#record");
        }
    }





}
