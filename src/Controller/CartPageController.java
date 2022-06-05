package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Stationary;
import model.cartItem;
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

public class CartPageController extends CartItemController implements Initializable
{
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private GridPane grid2;
    @FXML
    public Label totalp;
    @FXML
    private Button backButton;



    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();
    Statement st = connectDB.createStatement();

    private List<cartItem> cartItems = new ArrayList<>();

    public CartPageController() throws SQLException, IOException {
        super();
    }
    public String getname(int id) throws SQLException {
        String prodname = "SELECT ProductName FROM cart WHERE ProductID =" + id;
        ResultSet rs = st.executeQuery(prodname);
        rs.next();
        String Prodname = rs.getString(1);
        return Prodname;
    }
    public float getprice(int id) throws SQLException {
        String price = "SELECT Price FROM cart WHERE ProductID =" + id;
        ResultSet rs = st.executeQuery(price);
        rs.next();
        float Price = rs.getFloat(1);
        return Price;
    }
    public int getquan(int id) throws SQLException {
        String quan = "SELECT Quantity FROM cart WHERE ProductID =" + id;
        ResultSet rs = st.executeQuery(quan);
        rs.next();
        int Quantity = rs.getInt(1);
        return Quantity;
    }

    public String getnamefromtable(int id) throws SQLException {
        String prodname = "SELECT ProductName FROM mumbai WHERE ProductID =" + id;
        ResultSet rs = st.executeQuery(prodname);
        rs.next();
        String Prodname = rs.getString(1);
        return Prodname;
    }
    public float getpricefromtable(int id) throws SQLException {
        String price = "SELECT Price FROM mumbai WHERE ProductID =" + id;
        ResultSet rs = st.executeQuery(price);
        rs.next();
        float Price = rs.getFloat(1);
        return Price;
    }
    public String getimgfromtable(int id) throws SQLException {
        String img = "SELECT ImageSrc FROM mumbai WHERE ProductID =" + id;
        ResultSet rs = st.executeQuery(img);
        rs.next();
        String image = rs.getString(1);
        return image;
    }
    public List<cartItem> getData() throws SQLException {
        List<Integer>productID = getProdId();
        List<cartItem> cartItems = new ArrayList<>();
        cartItem CartItem;
        for(int i=0;i<productID.size();i++)
        {
            CartItem = new cartItem();
            CartItem.setItemCode(productID.get(i));
            CartItem.setName(getname(productID.get(i)));
            CartItem.setPrice(getprice(productID.get(i)));
            CartItem.setQuantity(getquan(productID.get(i)));
            cartItems.add(CartItem);
        }
        totalp.setText(totalprice());
        return cartItems;

    }

    public String totalprice() throws SQLException {
        List<Integer>productID = getProdId();
        double total = 0;
        int q=0;
        float p=0;
        for(int i=0;i<productID.size();i++)
        {
            q = getquan(productID.get(i));
            p = getprice(productID.get(i));
            total = total + ((double)(q)*(double)(p));
        }
        return (total + Main.CURRENCY);

    }
    public List<Integer> recIds = new ArrayList<>();

    public void getRecIds() throws SQLException {
        List<Integer>productID = getProdId();


        for(int i =0;i<productID.size();i++)
        {
            String rec = "SELECT RecommendedID FROM mumbai WHERE ProductID =" + productID.get(i);
            ResultSet rs = st.executeQuery(rec);
            rs.next();
            int recId = rs.getInt(1);
            recIds.add(recId);
        }


    }

    private List<Stationary> pens = new ArrayList<>();

    private List<Stationary>getdata() throws SQLException {
        List<Stationary> pens = new ArrayList<>();
        List<Integer> pr = new ArrayList<>(); //Store Recommended ProductID
        getRecIds();
        Stationary pen;
        for (int i=0;i<recIds.size();i++)
        {
            String prod = "SELECT ProductID FROM mumbai WHERE RecommendedID = " + recIds.get(i);
            ResultSet rs = st.executeQuery(prod);
            while ( rs.next())
            {
                int prodId = rs.getInt(1);
                pr.add(prodId);
            }
        }
        for (int i=0;i<pr.size();i++)
        {
            pen = new Stationary();
            pen.setItemCode(String.valueOf(pr.get(i)));
            pen.setName(getnamefromtable(pr.get(i)));
            pen.setPrice(getpricefromtable(pr.get(i)));
            pen.setImgSrc(getimgfromtable(pr.get(i)));
            pens.add(pen);
        }

        return pens;

    }
    public void homepage() throws IOException
    {
        Main m = new Main();
        m.changeScene("mainscreen.fxml");
    }
    public void checkout() throws IOException, SQLException {
        String cartClear = "DELETE FROM cart";
        st.executeUpdate(cartClear);
        Main m = new Main();
        m.changeScene("bye.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cartItems.addAll(getData());
            pens.addAll(getdata());
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        int column = 0;
        int row = 0;
        try
        {
            for (int i =0; i<cartItems.size();i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/cartItem.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                CartItemController cartItemController = fxmlLoader.getController();
                cartItemController.setData(cartItems.get(i));

                if(column==1)
                {
                    column = 0;
                    row++;
                }
                grid.add((anchorPane),column++,row);
                ///set grid width
                grid.setMinWidth(200);
                grid.setPrefWidth(200);
                grid.setMaxWidth(200);

                //set grid height
                grid.setMinHeight(200);
                grid.setPrefHeight(200);
                grid.setMaxHeight(200);

                GridPane.setMargin(anchorPane,new Insets(10));
            }
            for (int i =0; i<pens.size();i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController ItemController = fxmlLoader.getController();
                ItemController.setData(pens.get(i));


                grid2.add((anchorPane),column++,row);
                ///set grid width
                grid2.setMinWidth(200);
                grid2.setPrefWidth(200);
                grid2.setMaxWidth(200);

                //set grid height
                grid2.setMinHeight(200);
                grid2.setPrefHeight(200);
                grid2.setMaxHeight(200);

                GridPane.setMargin(anchorPane,new Insets(20));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
