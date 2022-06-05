package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.cartItem;
import views.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartItemController extends AddToCart
{
    @FXML
    private Label itemLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Label prodId;

    @FXML
    private Label priceLabel;
    @FXML
    private Button add;
    @FXML
    private Button sub;
    @FXML
    private Button deleteItem;

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    Statement st = connectDB.createStatement();
    public void cartpage() throws IOException {
        Main m = new Main();
        m.changeScene("checkoutcart2.fxml");
    }



    public List<Integer> getProdId() throws SQLException {
        List<Integer> productIdList = new ArrayList<>();
        String getProd = "select ProductId from cart;";
        ResultSet rs = st.executeQuery(getProd);
        int prodId = 0;
        while(rs.next())
        {
            prodId=rs.getInt(1);
            productIdList.add(prodId);
        }
        return productIdList;
    }
    public String getcode() {
        return prodId.getText();
    }

    public int getquan()
    {
        return Integer.parseInt(quantityLabel.getText());
    }
    private cartItem cartItem;
    List<Integer> productIdList = new ArrayList<>();

    public void setData(cartItem cartItem)
    {
        this.cartItem = cartItem;
        itemLabel.setText(cartItem.getName());
        priceLabel.setText(Main.CURRENCY + cartItem.getPrice());
        quantityLabel.setText(String.valueOf(cartItem.getQuantity()));
        prodId.setText(String.valueOf(cartItem.getItemCode()));

    }
    public CartItemController() throws SQLException, IOException {
        super();
    }
    public void addQuantity() throws SQLException, IOException {
        addToCart(Integer.parseInt(getcode()),1,"mumbai");
        cartpage();
    }
    public void subtractQuantity() throws SQLException, IOException {
        if(getquan()>1)
        {
            String quan = "update cart set Quantity = Quantity - 1 where ProductID =" + getcode();
            st.executeUpdate(quan);
            String tableQuantity = "update mumbai set Quantity = Quantity + 1 where ProductID =" + getcode();
            st.executeUpdate(tableQuantity);
            cartpage();
        }
        else if(getquan()==1)
        {
            deleteItem();
        }
    }
    public void deleteItem() throws SQLException, IOException {
        String updateSto = "update mumbai set Quantity = Quantity + " + getquan() + " where ProductID = " + getcode();
        st.executeUpdate(updateSto);
        String delete = "delete from cart where ProductID =" + getcode();
        st.executeUpdate(delete);
        cartpage();
    }

}
