package com.example.roommatesshopping;

public class ShoppingList {

    private String name;

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

    @Override
    public String toString() {
        return "ShoppingList{" +
                "name='" + name + '\'' +
                '}';
    }
}
