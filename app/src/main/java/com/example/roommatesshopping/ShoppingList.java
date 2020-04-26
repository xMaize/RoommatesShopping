package com.example.roommatesshopping;

public class ShoppingList {

    private String name;
    private String key;

    public ShoppingList(){

        this.name = null;
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

    @Override
    public String toString() {
        return "ShoppingList{" +
                "name='" + name + '\'' +
                '}';
    }
}
