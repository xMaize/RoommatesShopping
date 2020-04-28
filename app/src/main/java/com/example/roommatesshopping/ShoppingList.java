package com.example.roommatesshopping;

public class ShoppingList {

    private String name;
    private String key;
    private String purchasedList;

    public ShoppingList(){

        this.name = null;
        this.purchasedList = null;
    }

    public ShoppingList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() { return key;}

    public void setKey(String key) { this.key = key; }

    public String getPurchasedList() {
        return purchasedList;
    }

    public void setPurchasedList(String purchasedList) {
        this.purchasedList = purchasedList;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "name='" + name + '\'' +
                '}';
    }
}
