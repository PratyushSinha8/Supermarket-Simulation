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

public class EraserPageController implements Initializable
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
    private Button sharpnerButton;

    @FXML
    private Button pensButton;

    @FXML
    private Button compassButton;
    @FXML
    private Button cartButton;
    @FXML
    private Label topPicks;

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    Statement st = connectDB.createStatement();

    public EraserPageController() throws SQLException, IOException {
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

    private List<Stationary> erasers = new ArrayList<>();

    private List<Stationary>getData() throws SQLException {
        List<Stationary> erasers = new ArrayList<>();
        Stationary eraser;

        eraser = new Stationary();
        eraser.setItemCode("190701006");
        eraser.setName(getname(190701006));
        eraser.setPrice(getprice(190701006));
        eraser.setImgSrc("/img/eraser.jpg");
        erasers.add(eraser);

        eraser = new Stationary();
        eraser.setItemCode("190701007");
        eraser.setName(getname(190701007));
        eraser.setPrice(getprice(190701007));
        eraser.setImgSrc("/img/eraser2.jpg");
        erasers.add(eraser);

        eraser = new Stationary();
        eraser.setItemCode("190701008");
        eraser.setName(getname(190701008));
        eraser.setPrice(getprice(190701008));
        eraser.setImgSrc("/img/eraser5.png");
        erasers.add(eraser);

        eraser = new Stationary();
        eraser.setItemCode("190701009");
        eraser.setName(getname(190701009));
        eraser.setPrice(getprice(190701009));
        eraser.setImgSrc("/img/eraser4.png");
        erasers.add(eraser);

        eraser = new Stationary();
        eraser.setItemCode("190701010");
        eraser.setName(getname(190701010));
        eraser.setPrice(getprice(190701010));
        eraser.setImgSrc("/img/eraser3.png");
        erasers.add(eraser);

        return erasers;

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
    public void sharpnerpage() throws IOException
    {
        Main m = new Main();
        m.changeScene("sharpnerPage.fxml");
    }
    public void penPage() throws IOException {
        Main m = new Main();
        m.changeScene("penPage.fxml");
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
    public void pencilpage() throws IOException {
        Main m = new Main();
        m.changeScene("pencilPage.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try
        {
            erasers.addAll(getData());
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        int column = 0;
        int row = 0;
        try
        {
            for (int i =0; i<erasers.size();i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(erasers.get(i));

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


