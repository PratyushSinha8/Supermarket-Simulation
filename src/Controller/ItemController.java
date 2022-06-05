package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Stationary;
import views.Main;

import java.io.IOException;
import java.sql.SQLException;

public class ItemController extends AddToCart
{
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView img;

    @FXML
    private Button addToCart;

    @FXML
    private TextField quantity;

    @FXML
    private Label productId;

    @FXML
    private Label error;


    private Stationary stationary;

    public ItemController() throws SQLException, IOException {
        super();
    }

    public void setData(Stationary stationary)
    {
        this.stationary = stationary;
        productId.setText(stationary.getItemCode());
        nameLabel.setText(stationary.getName());
        priceLabel.setText(Main.CURRENCY + stationary.getPrice());
        Image image = new Image(getClass().getResourceAsStream(stationary.getImgSrc()));
        img.setImage(image);

    }
    public int getItemLabel() {
        return Integer.parseInt(productId.getText());
    }

    public int getQuantity() {
        if(quantity.getText()=="")
        {
            return 0;
        }
        else
        {
            return Integer.parseInt(quantity.getText());
        }
    }


    public void cart() throws SQLException {
        if(getQuantity()<10 && getQuantity()>0)
        {
            error.setText("Item Added");
            int ret=addToCart(getItemLabel(),getQuantity(),"mumbai");

            if(ret==1)
            {
                error.setText("SOLD OUT");
            }
        }
       else if (getQuantity()<=0)
        {
            error.setText("ERROR!!");
        }
       else if(getQuantity()>10)
        {
            error.setText("MAX Quantity is 10");
        }

    }


}

