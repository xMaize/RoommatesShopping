package com.example.roommatesshopping;

public class RecentlyPurchasedList {

    private int id;

    public RecentlyPurchasedList(){
        this.id = -1;
    }

    public RecentlyPurchasedList(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "RecentlyPurchasedList{" +
                "id=" + id +
                '}';
    }

}
