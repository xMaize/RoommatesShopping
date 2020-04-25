package com.example.roommatesshopping;

public class Item {

    private String itemName;
    private int quantity;
    private String nameOfPurchaser;
    private double price;

    public Item(){
        this.itemName = null;
        this.quantity = 0;
        this.nameOfPurchaser = null;
        this.price = 0;
    }

    public Item(String itemName, int count, String nameOfPurchaser, double price){
        this.itemName = itemName;
        this.quantity = count;
        this.nameOfPurchaser = nameOfPurchaser;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int count) {
        this.quantity = count;
    }

    public String getNameOfPurchaser() {
        return nameOfPurchaser;
    }

    public void setNameOfPurchaser(String nameOfPurchaser) {
        this.nameOfPurchaser = nameOfPurchaser;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", nameOfPurchaser='" + nameOfPurchaser + '\'' +
                ", price=" + price +
                '}';
    }
}
