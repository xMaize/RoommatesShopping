package com.example.roommatesshopping;

public class ShoppingList {

    private int id;

    public ShoppingList(){
        this.id = -1;
    }

    public ShoppingList(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                '}';
    }

}
