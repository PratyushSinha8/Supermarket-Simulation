package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import views.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterController
{
    @FXML
    private TextField usernameField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField pincodeField;
    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;
    @FXML
    private Label registered;

    public void loginpage() throws IOException
    {
        Main m = new Main();
        m.changeScene("login.fxml");
    }

    public void registerUser() throws SQLException {
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();

        String username = usernameField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();
        String address = addressField.getText();
        String pincode = pincodeField.getText();

        String verifyUsername = "SELECT count(1) FROM user_account WHERE username = '" + usernameField.getText() + "'";
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery(verifyUsername);

        if(usernameField.getText()==""|| nameField.getText()==""|| pincodeField.getText()==""|| addressField.getText()==""|| passwordField.getText()=="")
        {
            registered.setText("Please Enter Everything");
        }
        else
        {
            while (rs.next())
            {
                if(rs.getInt(1)==1)
                {
                    registered.setText("Username Taken!!!");
                }
                else
                {
                    String insertFields = "INSERT INTO user_account(name, username, password, address, pincode) VALUES ('";
                    String insertValues = name + "','" + username + "','" + password + "','" + address + "','" + pincode + "')";
                    String insertToRegister = insertFields + insertValues;

                    try
                    {
                        statement.executeUpdate(insertToRegister);
                        registered.setText("REGISTRATION SUCCESSFUL!!!");
                        break;
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                        e.getCause();
                    }
                }
            }


        }

    }
}
