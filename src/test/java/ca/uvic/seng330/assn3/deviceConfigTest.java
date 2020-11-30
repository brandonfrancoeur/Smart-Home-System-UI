package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Temperature;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;



public class deviceConfigTest extends ApplicationTest {

    private Scene scene;

    loginModel model = DesktopPaneTest.getModel();

    @Override
    public void start(Stage primaryStage) {

        deviceConfigController controller = new deviceConfigController(model);
        deviceConfig_page view  = new deviceConfig_page(controller);

        scene = new Scene(view.deviceConfig_page(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
