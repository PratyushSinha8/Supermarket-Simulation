package model;

public class cartItem
{
    private String name;
    private int quantity;
    private double price;
    private int itemCode;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }

}
