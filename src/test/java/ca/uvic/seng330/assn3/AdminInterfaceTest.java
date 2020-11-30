package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.devices.Temperature;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class AdminInterfaceTest extends ApplicationTest {

    private Scene scene;
    static loginModel model;

    @Override
    public void start(Stage primaryStage) {

        adminController controller = new adminController(DesktopPaneTest.getModel());
        admin_page view2 = new admin_page(controller);

        scene = new Scene(view2.admin_page(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
 tries to add a device from admin interface
 assume
 button: add_device
 */
    @Test
    public void Admin_add_device() {
        clickOn("#manage_devices");
    }
  /*
  tries to find the list of devices
  assume
  the list is in the admin interface
  button: device_list
  */
    @Test
    public void Admin_find_logout() {
        clickOn("logout");
    }

    @Test
    public void Admin_find_manage_user() {
        clickOn("#removeUser");
    }

    @Test
    public void Admin_find_config_devices() {
        clickOn("#configDevices");
    }

}

