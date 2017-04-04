package com.messwalaapp.bean;

/**
 * Created by Aakash Jaiswal on 3/1/2017.
 */

public class SignIn {
    //registration fields
    private String name;
    private String email;
    private String password;
    private String phone;


    //setter and getter functions
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
