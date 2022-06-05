package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddToCart
{
    public AddToCart() throws SQLException, IOException {
    }

    public int addToCart(int prod_id, int req_quantity, String t_name) throws SQLException
    { //Return type could be changed to 1, 0, -1 for future purpose
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        Statement st = connectDB.createStatement();
        String checkQuantQ = "select quantity from " + t_name + " where ProductID = " + prod_id;
        ResultSet rs = st.executeQuery(checkQuantQ);
        rs.next();
        int Quant = rs.getInt(1); //Make sure this works

        String checkCart = "SELECT count(1) FROM cart WHERE EXISTS (SELECT ProductID FROM" +t_name+ " WHERE ProductID = " + prod_id + " )";
        rs = st.executeQuery(checkCart);
        rs.next();
        int check = rs.getInt(1);
        System.out.println(check);

        if(check==0)
        {
            if (req_quantity <= Quant) {//If requested amount is lesser than available amount

                //Update Cart
                String updateCart = "insert into cart(StoreID, ProductID,ProductName, Price)\n" +
                        " select StoreID, ProductID,ProductName, Price from " + t_name + " where ProductID = " + prod_id;
                st.executeUpdate(updateCart);

                //Update Store
                String updateStore = "update " + t_name + " set Quantity = Quantity-" + req_quantity + " where ProductID=" + prod_id;
                st.executeUpdate(updateStore);

                //Update Cart Quantity
                String updateQuant = "update cart set Quantity=" + req_quantity + " where ProductID=" + prod_id;
                st.executeUpdate(updateQuant);
                return 0;
            }
            else
            {
                return 1;
            }

        }
        else
        {
            String checkCartQ = "select quantity from cart where ProductID = " + prod_id;
            rs = st.executeQuery(checkCartQ);
            rs.next();
            int checkCartQuan = rs.getInt(1);
            if(checkCartQuan + req_quantity <=10)
            {
                if (req_quantity <= Quant) {//If requested amount is lesser than available amount

                    //Update Store
                    String updateStore = "update " + t_name + " set Quantity = Quantity-" + req_quantity + " where ProductID=" + prod_id;
                    st.executeUpdate(updateStore);

                    //Update Cart Quantity
                    String updateQuant = "update cart set Quantity = Quantity +" + req_quantity + " where ProductID=" + prod_id;
                    st.executeUpdate(updateQuant);
                    return 0;
                }
            }
            return 1;

        }

    }
}
