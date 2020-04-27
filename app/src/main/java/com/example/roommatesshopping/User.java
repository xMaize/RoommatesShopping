package com.example.roommatesshopping;

public class User {

    private String userEmail;
    private String userHousehold;

    public User (){
        this.userEmail = null;
    }

    public User (String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserHousehold() {
        return userHousehold;
    }

    public void setUserHousehold(String userHousehold) {
        this.userHousehold = userHousehold;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                '}';
    }
}
