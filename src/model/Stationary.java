package model;

public class Stationary {
    private String name;
    private String imgSrc;
    private double price;
    private String itemCode;


    public String getItemCode() {
        return itemCode;
    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
