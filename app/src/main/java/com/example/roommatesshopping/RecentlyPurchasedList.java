package com.example.roommatesshopping;

public class RecentlyPurchasedList {

    private String name;

    public RecentlyPurchasedList(){
        this.name = null;
    }

    public RecentlyPurchasedList(String name){
        this.name = name;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String toString() {
        return "RecentlyPurchasedList{" +
                "name=" + name +
                '}';
    }

}
