package Controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.Stationary;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CompassPageController implements Initializable
{
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    @FXML
    private Button logout;
    @FXML
    private Button sharpnerButton;
    @FXML
    private Button eraserButton;
    @FXML
    private Button cartButton;
    @FXML
    private Button pensButton;
    @FXML
    private Label topPicks;

    @FXML
    private Button home;
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    Statement st = connectDB.createStatement();

    public CompassPageController() throws SQLException, IOException {

    }

    public String getname(int id) throws SQLException {
        String prodname = "SELECT ProductName FROM mumbai WHERE ProductID =" + id;
        ResultSet rs = st.executeQuery(prodname);
        rs.next();
        String Prodname = rs.getString(1);
        return Prodname;
    }
    public float getprice(int id) throws SQLException {
        String price = "SELECT Price FROM mumbai WHERE ProductID =" + id;
        ResultSet rs = st.executeQuery(price);
        rs.next();
        float Price = rs.getFloat(1);
        return Price;
    }


    private List<Stationary> compasses = new ArrayList<>();

    private List<Stationary>getData() throws SQLException {
        List<Stationary> compasses = new ArrayList<>();
        Stationary compass;

        compass = new Stationary();
        compass.setItemCode("190701016");
        compass.setName(getname(190701016));
        compass.setPrice(getprice(190701016));
        compass.setImgSrc("/img/box1.PNG");
        compasses.add(compass);

        compass = new Stationary();
        compass.setItemCode("190701017");
        compass.setName(getname(190701017));
        compass.setPrice(getprice(190701017));
        compass.setImgSrc("/img/box2.PNG");
        compasses.add(compass);

        compass = new Stationary();
        compass.setItemCode("190701018");
        compass.setName(getname(190701018));
        compass.setPrice(getprice(190701018));
        compass.setImgSrc("/img/box4.PNG");
        compasses.add(compass);

        compass = new Stationary();
        compass.setItemCode("190701019");
        compass.setName(getname(190701019));
        compass.setPrice(getprice(190701019));
        compass.setImgSrc("/img/box3.PNG");
        compasses.add(compass);

        compass = new Stationary();
        compass.setItemCode("190701020");
        compass.setName(getname(190701020));
        compass.setPrice(getprice(190701020));
        compass.setImgSrc("/img/box5.PNG");
        compasses.add(compass);
        return compasses;

    }
    public void homepage() throws IOException
    {
        Main m = new Main();
        m.changeScene("mainscreen.fxml");
    }
    public void loginpage() throws IOException
    {
        Main m = new Main();
        m.changeScene("login.fxml");
    }
    public void eraserpage() throws IOException
    {
        Main m = new Main();
        m.changeScene("eraserPage.fxml");
    }
    public void sharpnerpage() throws IOException
    {
        Main m = new Main();
        m.changeScene("sharpnerPage.fxml");
    }

    public void penPage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("penPage.fxml");
    }
    public void cartpage() throws IOException {
        Main m = new Main();
        m.changeScene("checkoutcart2.fxml");
    }
    public void pencilpage() throws IOException {
        Main m = new Main();
        m.changeScene("pencilPage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try
        {
            compasses.addAll(getData());
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        int column = 0;
        int row = 0;
        try
        {
            for (int i =0; i<compasses.size();i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(compasses.get(i));

                if(column >= 6)
                {
                    column = 0;
                    row++;
                }
                grid.add((anchorPane),column=column+2,row);
                ///set grid width
                grid.setMinWidth(100);
                grid.setPrefWidth(100);
                grid.setMaxWidth(100);

                //set grid height
                grid.setMinHeight(100);
                grid.setPrefHeight(100);
                grid.setMaxHeight(100);

                GridPane.setMargin(anchorPane,new Insets(30));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(topPicks);
        translate.setDuration(Duration.millis(10000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(350);
        translate.setAutoReverse(true);
        translate.play();
    }
}


