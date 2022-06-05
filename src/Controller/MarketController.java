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

public class MarketController implements Initializable
{
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button pensButton;
    @FXML
    private Button eraserButton;
    @FXML
    private Button sharpnerButton;
    @FXML
    private Button compassButton;
    @FXML
    private Button cartButton;

    @FXML
    private Button logout;
    @FXML
    private Label topPicks;

    private List<Stationary> stationaries = new ArrayList<>();

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    Statement st = connectDB.createStatement();

    public MarketController() throws SQLException, IOException {

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

    private List<Stationary>getData() throws SQLException {
        List<Stationary> stationaries = new ArrayList<>();
        Stationary stationary;

        stationary = new Stationary();
        stationary.setItemCode("190701001");
        stationary.setName(getname(190701001));
        stationary.setPrice(getprice(190701001));
        stationary.setImgSrc("/img/pen.jpg");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701002");
        stationary.setName(getname(190701002));
        stationary.setPrice(getprice(190701002));
        stationary.setImgSrc("/img/pen2.jpg");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701003");
        stationary.setName(getname(190701003));
        stationary.setPrice(getprice(190701003));
        stationary.setImgSrc("/img/pen11.PNG");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701021");
        stationary.setName(getname(190701021));
        stationary.setPrice(getprice(190701021));
        stationary.setImgSrc("/img/pencil.jpg");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701022");
        stationary.setName(getname(190701022));
        stationary.setPrice(getprice(190701022));
        stationary.setImgSrc("/img/pencil2.jpg");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701023");
        stationary.setName(getname(190701023));
        stationary.setPrice(getprice(190701023));
        stationary.setImgSrc("/img/pencil11.PNG");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701006");
        stationary.setName(getname(190701006));
        stationary.setPrice(getprice(190701006));
        stationary.setImgSrc("/img/eraser.jpg");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701007");
        stationary.setName(getname(190701007));
        stationary.setPrice(getprice(190701007));
        stationary.setImgSrc("/img/eraser2.jpg");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701008");
        stationary.setName(getname(190701008));
        stationary.setPrice(getprice(190701008));
        stationary.setImgSrc("/img/eraser5.PNG");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701011");
        stationary.setName(getname(190701011));
        stationary.setPrice(getprice(190701011));
        stationary.setImgSrc("/img/sharpener.jpg");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701012");
        stationary.setName(getname(190701012));
        stationary.setPrice(getprice(190701012));
        stationary.setImgSrc("/img/sharpener1.PNG");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701013");
        stationary.setName(getname(190701013));
        stationary.setPrice(getprice(190701013));
        stationary.setImgSrc("/img/sharpener2.PNG");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701016");
        stationary.setName(getname(190701016));
        stationary.setPrice(getprice(190701016));
        stationary.setImgSrc("/img/box1.PNG");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701017");
        stationary.setName(getname(190701017));
        stationary.setPrice(getprice(190701017));
        stationary.setImgSrc("/img/box2.PNG");
        stationaries.add(stationary);

        stationary = new Stationary();
        stationary.setItemCode("190701018");
        stationary.setName(getname(190701018));
        stationary.setPrice(getprice(190701018));
        stationary.setImgSrc("/img/box3.PNG");
        stationaries.add(stationary);

        return stationaries;

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
        try {
            stationaries.addAll(getData());
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
            int column = 0;
            int row = 0;
            try
            {
                for (int i =0; i<stationaries.size();i++)
                {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(stationaries.get(i));

                    if(column >= 6)
                    {
                        column = 0;
                        row++;
                    }
                    grid.add((anchorPane),column=column+2,row);
                    ///set grid width
                    grid.setMinWidth(200);
                    grid.setPrefWidth(200);
                    grid.setMaxWidth(200);

                    //set grid height
                    grid.setMinHeight(200);
                    grid.setPrefHeight(200);
                    grid.setMaxHeight(200);

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


