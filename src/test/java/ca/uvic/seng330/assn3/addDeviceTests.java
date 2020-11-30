package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Temperature;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class addDeviceTests extends ApplicationTest {

    private Scene scene;
    loginModel model = DesktopPaneTest.getModel();

    @Override
    public void start(Stage primaryStage) {

        deviceConfigController controller = new deviceConfigController(model);
        device_page view  = new device_page(controller);

        scene = new Scene(view.device_page(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
