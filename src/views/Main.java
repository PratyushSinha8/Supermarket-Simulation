package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static final String CURRENCY = "₹";


    private static Stage stg;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/selectcity.fxml"));
        primaryStage.setTitle("Stationary Delivery Application");
        primaryStage.setScene(new Scene(root, 1050, 750));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void changeScene(String fxml) throws IOException
    {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
