package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.city;
import views.Main;

import java.io.IOException;

public class TableItem
{
    @FXML
    private Label tableLabel;
    @FXML
    private ImageView img;

    private city city;

    public String homepage() throws IOException {
        Main m = new Main();
        m.changeScene("mainscreen.fxml");
        String tableName = tableName();
        return tableName;
    }
    public void setData(city city)
    {
        this.city = city;
        tableLabel.setText(city.getCityname());
        Image image = new Image(getClass().getResourceAsStream(city.getImgSrc()));
        img.setImage(image);
    }

    public String tableName() {
        return tableLabel.getText();
    }

}
