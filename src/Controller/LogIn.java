package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import views.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LogIn {

    public LogIn() {

    }

    @FXML
    private Button login;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button Register;

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }
    public void register(ActionEvent event) throws IOException
    {
        Main m = new Main();
        m.changeScene("register.fxml");
    }
    public void homepage() throws IOException
    {
        Main m = new Main();
        m.changeScene("mainscreen.fxml");
    }

    private void checkLogin() throws IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + username.getText() + "' AND password = '" + password.getText() + "'";
        try
        {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while ((queryResult.next()))
            {
                if(queryResult.getInt(1) == 1)
                {
                    wrongLogIn.setText("SUCCESS!!!");
                    homepage();
                }
                else
                {
                    wrongLogIn.setText("Invalid login");
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }


}