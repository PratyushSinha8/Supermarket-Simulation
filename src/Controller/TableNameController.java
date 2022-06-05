package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Stationary;
import model.city;
import views.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TableNameController implements Initializable
{

    @FXML
    private Button back;
    @FXML
    private Button mumbai;
    @FXML
    private Button nagpur;
    @FXML
    private Button nashik;
    @FXML
    private Button pune;
    @FXML
    private GridPane grid;

    public TableNameController() throws SQLException, IOException
    {

    }
    private List<city> cities = new ArrayList<>();
    public void homepage() throws IOException {
        Main m = new Main();
        m.changeScene("mainscreen.fxml");
    }
    public void loginpage() throws IOException
    {
        Main m = new Main();
        m.changeScene("Login.fxml");
    }
    private List<Stationary> compasses = new ArrayList<>();

    private List<city>getData() throws SQLException {
        List<city> cities = new ArrayList<>();
        city cityname;
        cityname = new city();
        cityname.setCityname("mumbai");
        cityname.setImgSrc("/buttonImg/selectcity1 (2).PNG");
        cities.add(cityname);

        cityname = new city();
        cityname.setCityname("pune");
        cityname.setImgSrc("/buttonImg/selectcity2 (2).PNG");
        cities.add(cityname);

        cityname = new city();
        cityname.setCityname("nashik");
        cityname.setImgSrc("/buttonImg/selectcity3 (2).PNG");
        cities.add(cityname);

        cityname = new city();
        cityname.setCityname("nagpur");
        cityname.setImgSrc("/buttonImg/selectcity4.PNG");
        cities.add(cityname);

        return cities;

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try
        {
            cities.addAll(getData());
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        int column = 0;
        int row = 0;
        try
        {
            for (int i =0; i<cities.size();i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/selectcityItem.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                TableItem tableItem= fxmlLoader.getController();
                tableItem.setData(cities.get(i));

                grid.add((anchorPane),column=column+3,row);
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
    }

}
