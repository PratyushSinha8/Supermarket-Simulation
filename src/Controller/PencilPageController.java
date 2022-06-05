package Controller;

import javafx.animation.TranslateTransition;
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

public class PencilPageController implements Initializable
{
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    @FXML
    private Button logout;

    @FXML
    private Button home;
    @FXML
    private Label topPicks;

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    Statement st = connectDB.createStatement();


    public PencilPageController() throws SQLException {
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


    private List<Stationary> pencils = new ArrayList<>();

    private List<Stationary>getData() throws SQLException {
        List<Stationary> pencils = new ArrayList<>();
        Stationary pencil;

        pencil = new Stationary();
        pencil.setItemCode("190701021");
        pencil.setName(getname(190701021));
        pencil.setPrice(getprice(190701021));
        pencil.setImgSrc("/img/pencil.jpg");
        pencils.add(pencil);

        pencil = new Stationary();
        pencil.setItemCode("190701022");
        pencil.setName(getname(190701022));
        pencil.setPrice(getprice(190701022));
        pencil.setImgSrc("/img/pencil2.jpg");
        pencils.add(pencil);

        pencil = new Stationary();
        pencil.setItemCode("190701023");
        pencil.setName(getname(190701023));
        pencil.setPrice(getprice(190701023));
        pencil.setImgSrc("/img/pencil11.PNG");
        pencils.add(pencil);

        pencil = new Stationary();
        pencil.setItemCode("190701024");
        pencil.setName(getname(190701024));
        pencil.setPrice(getprice(190701024));
        pencil.setImgSrc("/img/pencil12.PNG");
        pencils.add(pencil);

        pencil = new Stationary();
        pencil.setItemCode("190701025");
        pencil.setName(getname(190701025));
        pencil.setPrice(getprice(190701025));
        pencil.setImgSrc("/img/pencil13.PNG");
        pencils.add(pencil);

        return pencils;

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
    public void compasspage() throws IOException
    {
        Main m = new Main();
        m.changeScene("compassPage.fxml");
    }
    public void cartpage() throws IOException {
        Main m = new Main();
        m.changeScene("checkoutcart2.fxml");
    }
    public void penPage() throws IOException {
        Main m = new Main();
        m.changeScene("penPage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try
        {
            pencils.addAll(getData());
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        int column = 0;
        int row = 0;
        try
        {
            for (int i =0; i<pencils.size();i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(pencils.get(i));

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


