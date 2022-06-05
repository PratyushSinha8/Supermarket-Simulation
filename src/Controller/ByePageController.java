package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import views.Main;

import java.io.IOException;

public class ByePageController
{
    @FXML
    private Button jhakaas;

    public void loginpage() throws IOException {
        Main m = new Main();
        m.changeScene("Login.fxml");
    }
}
